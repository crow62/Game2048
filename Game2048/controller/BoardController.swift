//
//  BoardController.swift
//  Game2048
//
//  Created by Дмитрий Мелешин on 11.12.2022.
//

import UIKit

class BoardController: UIViewController {
    
    lazy var game: Game = Game(numberOfFields: 0)
    
    @IBOutlet var labels: [UILabel]!
    
    @IBOutlet weak var scoreLabel: UILabel!
    
    @IBOutlet weak var gameLabel: UILabel!
    
    @IBOutlet weak var bestLabel: UILabel!
    
    @IBOutlet weak var menuButton: UIButton!
    
    @IBOutlet weak var leaderboardButton: UIButton!
    
    @IBOutlet weak var b: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initViewBoard()
        game = Game(numberOfFields: labels.count)
        scoreLabel.text = ""
        updateLabels()
        swipesObservers()
    }
    
    private func formLabel(label: UILabel) {
        label.layer.cornerRadius = 6
        label.layer.masksToBounds = true
        label.adjustsFontSizeToFitWidth = true
        label.minimumScaleFactor = 0
    }
    
    private func initViewBoard() {
        view.subviews.first?.layer.cornerRadius = 7
        labels.forEach {
            formLabel(label: $0)
        }
        formLabel(label: gameLabel)
        formLabel(label: scoreLabel)
        formLabel(label: bestLabel)
        
        menuButton.layer.cornerRadius = 5
        menuButton.titleLabel?.adjustsFontSizeToFitWidth = true
        menuButton.titleLabel?.sizeToFit()
        leaderboardButton.layer.cornerRadius = 5
        leaderboardButton.setTitle("LEADERBOARD", for: .normal)
        
    }
    
    
    
    private func updateLabels() {
        clearDataLabels()
        fillDataLabels()
    }
    
    private func computeScore() {
        scoreLabel.text = String(game.getAllFields().filter {!$0.isEmpty}.map {$0.value!}.reduce(0,+))
    }
    
    
    private func clearDataLabels() {
        labels.forEach { label in
            label.text = ""
            label.backgroundColor = #colorLiteral(red: 0.7911508083, green: 0.7515822053, blue: 0.7090365887, alpha: 1)
            label.textColor = #colorLiteral(red: 0.4647238851, green: 0.4301683307, blue: 0.3961300254, alpha: 1)
        }
    }
    
    private func fillDataLabels() {
        let fields = game.getAllFields().map { $0.value }
        for label in labels.enumerated() {
            if let value = fields[label.offset] {
                switch value {
                case 2:
                    label.element.backgroundColor = #colorLiteral(red: 0.9331909418, green: 0.8886690736, blue: 0.8505761027, alpha: 1)
                case 4:
                    label.element.backgroundColor = #colorLiteral(red: 0.9152235985, green: 0.8608649373, blue: 0.7662799954, alpha: 1)
                case 8:
                    label.element.backgroundColor = #colorLiteral(red: 0.9486302733, green: 0.6913644075, blue: 0.4729106426, alpha: 1)
                    label.element.textColor = .white
                case 16:
                    label.element.backgroundColor = #colorLiteral(red: 0.8764913082, green: 0.5202506185, blue: 0.3179527521, alpha: 1)
                    label.element.textColor = .white
                case 32:
                    label.element.backgroundColor = #colorLiteral(red: 0.9511354566, green: 0.4761331677, blue: 0.3669580221, alpha: 1)
                    label.element.textColor = .white
                case 64:
                    label.element.backgroundColor = #colorLiteral(red: 0.8922826648, green: 0.3350912631, blue: 0.2099007368, alpha: 1)
                    label.element.textColor = .white
                default:
                    label.element.backgroundColor = #colorLiteral(red: 0.9451281428, green: 0.8515697122, blue: 0.4230809808, alpha: 1)
                    label.element.textColor = .white
                }
                label.element.text = String(value)
            }
        }
    }
    
    
    func swipesObservers() {
        let swipeUp = UISwipeGestureRecognizer(target: self,
                                               action: #selector(handleSwipes))
        swipeUp.direction = UISwipeGestureRecognizer.Direction.up
        self.view.addGestureRecognizer(swipeUp)
        
        let swipeDown = UISwipeGestureRecognizer(target: self, action:#selector(handleSwipes))
        swipeDown.direction = UISwipeGestureRecognizer.Direction.down
        self.view.addGestureRecognizer(swipeDown)
        
        let swipeLeft = UISwipeGestureRecognizer(target: self, action:#selector(handleSwipes))
        swipeLeft.direction = UISwipeGestureRecognizer.Direction.left
        self.view.addGestureRecognizer(swipeLeft)
        
        let swipeRight = UISwipeGestureRecognizer(target: self, action:#selector(handleSwipes))
        swipeRight.direction = UISwipeGestureRecognizer.Direction.right
        self.view.addGestureRecognizer(swipeRight)
    }
    
    @objc func handleSwipes(gesture: UISwipeGestureRecognizer) throws {
        switch gesture.direction {
        case UISwipeGestureRecognizer.Direction.up:
            game.makeMove(direction: .up)
        case UISwipeGestureRecognizer.Direction.down:
            game.makeMove(direction: .down)
        case UISwipeGestureRecognizer.Direction.left:
            game.makeMove(direction: .left)
        case UISwipeGestureRecognizer.Direction.right:
            game.makeMove(direction: .right)
        default:
            throw GameError.unexpectedState
        }
        updateLabels()
        computeScore()
        
        if (game.boardIsFull && !game.canMove()) {
            performSegue(withIdentifier: "gameOver", sender: nil)
            viewDidLoad()
        }
    }
}
