//
//  NativeViewController.swift
//  Runner
//
//  Created by Aaron Blondeau on 10/26/18.
//  Copyright © 2018 The Chromium Authors. All rights reserved.
//

import UIKit
import Flutter

class NativeViewController: UIViewController {
    
    var selectedValue: Int = 0
    @IBOutlet weak var valueSlider: UISlider!
    var completionHandler:((Int) -> Void)?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    override func viewDidAppear(_ animated: Bool) {
        valueSlider.value = Float(selectedValue)
    }
    
    @IBAction func exitAction(_ sender: Any) {
        selectedValue = Int(valueSlider.value)
        completionHandler?(selectedValue)
        dismiss(animated: true, completion: nil)
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
