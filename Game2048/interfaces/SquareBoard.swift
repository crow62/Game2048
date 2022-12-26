//
//  SquareBoard.swift
//  Game2048
//
//  Created by Дмитрий Мелешин on 12.12.2022.
//

import Foundation

protocol SquareBoard {
    
    init(_ numberOfFields: Int )
    
    func getRow(by index: Int) -> [Field]
    
    func getColumn(by index: Int) -> [Field]
    
    func findField(by position: Position) throws -> Field
    
    func addField(newValue: Int)
    
    func getFieldsInAscendingPosition(filter: (Position) -> (Bool)) -> [Field]
    
    func getRows() -> [[Field]]
    
    func getColumns() -> [[Field]]
    
    func reversedLines(lines: [[Field]]) -> [[Field]]
}

