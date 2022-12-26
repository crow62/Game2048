//
//  Game2048Board.swift
//  Game2048
//
//  Created by Дмитрий Мелешин on 12.12.2022.
//

import Foundation

final class Game2048Board {
    
    private let numberOfColumnOrRows: Int
    
    private var fieldDictionary: [Position : Field]
    
    required init(_ numberOfFields: Int ) {
        self.numberOfColumnOrRows = Int(sqrt(Double(numberOfFields)))
        self.fieldDictionary = [Position: Field]()
        initFieldDictionary(numberOfColumnOrRows: numberOfColumnOrRows)
    }
    
    func initFieldDictionary( numberOfColumnOrRows: Int) {
        for i in (0..<numberOfColumnOrRows) {
            for k in (0..<numberOfColumnOrRows) {
                let position = Position(x: i, y: k)
                fieldDictionary[position] = Field()
            }
        }
    }
    
}

extension Game2048Board: SquareBoard {
    
    func getRow(by index: Int) -> [Field] {
        getFieldsInAscendingPosition(filter: { $0.x == index })
    }
    
    func getColumn(by index: Int) -> [Field] {
        getFieldsInAscendingPosition(filter: { $0.y == index })
    }
    
    func getFieldsInAscendingPosition(filter: (Position) -> (Bool)) -> [Field]
    {
        var fields = [Field]()
        self.fieldDictionary.keys.filter(filter)
            .sorted(by: < )
            .forEach { fields.append(self.fieldDictionary[$0]!)
            }
        return fields
    }
    
    func findField(by position: Position) throws -> Field {
        if let field = self.fieldDictionary[position] {
            return field
        } else {
            throw GameError.notFound
        }
    }
    
    func addField(newValue: Int) {
        let emptyFields = getEmptyFields()
        //generate '2' in random empty field
        emptyFields[Int.random(in: 0..<emptyFields.count)].value = newValue
    }
    
    private func getEmptyFields() -> [Field] {
        self.fieldDictionary.values.filter { $0.isEmpty }
        
    }

    func getRows() -> [[Field]] {
        var rows = [[Field]]()
        for i in 0..<numberOfColumnOrRows {
            rows.append(getRow(by: i))
        }
        return rows
    }
    
    func getColumns() -> [[Field]] {
        var columns = [[Field]]()
        for i in 0..<numberOfColumnOrRows {
            columns.append(getColumn(by: i))
        }
        return columns
    }
    
    func reversedLines(lines: [[Field]]) -> [[Field]]{
        var reversedLines = [[Field]]()
        for line in lines {
            reversedLines.append(line.reversed())
        }
        return reversedLines
    }
           
}
