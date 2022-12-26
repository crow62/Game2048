//
//  GameOverViewController.swift
//  Game2048
//
//  Created by Дмитрий Мелешин on 20.12.2022.
//

import UIKit

class GameOverViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

    }
    
    @IBAction func tryAgain(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }
}
