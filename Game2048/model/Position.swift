//
//  Position.swift
//  Game2048
//
//  Created by Дмитрий Мелешин on 11.12.2022.
//

import Foundation

struct Position: Hashable, Comparable {
    
    let x: Int
    let y: Int
    
    //sort Positions in ascending order -> 01..02..03..10..11..
    static func < (lhs: Position, rhs: Position) -> Bool {
        if lhs.x < rhs.x {
            return true
        } else if lhs.x > rhs.x {
            return false
        } else {
            return lhs.y < rhs.y
        }
    }
    
}
