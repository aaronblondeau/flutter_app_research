import UIKit
import Flutter

@UIApplicationMain
@objc class AppDelegate: FlutterAppDelegate {
  override func application(
    _ application: UIApplication,
    didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?
  ) -> Bool {
    GeneratedPluginRegistrant.register(with: self)
    
    let storyboard = UIStoryboard(name: "Main", bundle: Bundle.main)
    let controller : FlutterViewController = window?.rootViewController as! FlutterViewController;
    let methodChannel = FlutterMethodChannel.init(name: "flutter_app_research.salidasoftware.com/method",
                                                   binaryMessenger: controller);
    
    let messageChannel = FlutterBasicMessageChannel.init(name: "flutter_app_research.salidasoftware.com/message", binaryMessenger: controller, codec: FlutterStringCodec.sharedInstance())
    
    methodChannel.setMethodCallHandler({
        (call: FlutterMethodCall, result: @escaping FlutterResult) -> Void in
        if ("openNative" == call.method) {
            
            var defaultValue = 0
            if let args = call.arguments as! [String:Any]? {
                defaultValue = args["counter"] as! Int;
            }
            
            let nativeController = storyboard.instantiateViewController(withIdentifier: "nativeViewController") as! NativeViewController
            nativeController.selectedValue = defaultValue
            nativeController.completionHandler = { (value: Int) -> Void in
                result(value)
                messageChannel.sendMessage("MSG FROM NATIVE \(defaultValue + 1)") //["test":defaultValue + 1])
            }
            controller.present(nativeController, animated: true, completion: nil)
            
            // result(Int(defaultValue + 1));
            
        } else {
        }
    });
    
    messageChannel.setMessageHandler { (message: Any?, reply: FlutterReply) in
        if let msg = message as! String? {
            // print("~~ NATIVE GOT MESSAGE \(msg)")
            messageChannel.sendMessage("ECHO : \(msg)")
        }
        reply("")
    }
    
    return super.application(application, didFinishLaunchingWithOptions: launchOptions)
  }
}
