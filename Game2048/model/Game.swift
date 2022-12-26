//
//  Game.swift
//  Game2048
//
//  Created by Дмитрий Мелешин on 11.12.2022.
//

import Foundation

final class Game {
 
    private var fieldsOnPreviousMove = [Field]()
    
    private var board: SquareBoard

    private (set) var currenDirectionOfMove: Direction?
    
    
    private var movementHappened : Bool {
        fieldsOnPreviousMove != getAllFields()
    }
    
    var boardIsFull: Bool {
        getAllFields().filter {$0.isEmpty}.isEmpty
    }
        
    init(numberOfFields: Int) {
        self.board = Game2048Board(numberOfFields)
        generateNewValuesOnBoard()
    }
    
    private func generateNewValuesOnBoard() {
        Int.random(in: 0..<10) == 0 ?
        board.addField(newValue: 4) : board.addField(newValue: 2)
    }
    
    func canMove() -> Bool {
        makeMove(direction: .up)
        if movementHappened {
            var fields = getAllFields()
            updateFields(to: &fields, from: fieldsOnPreviousMove)
            return true
        }
        makeMove(direction: .down)
        if movementHappened {
            var fields = getAllFields()
            updateFields(to: &fields, from: fieldsOnPreviousMove)
            return true
        }
        
        makeMove(direction: .left)
        if movementHappened {
            var fields = getAllFields()
            updateFields(to: &fields, from: fieldsOnPreviousMove)
            return true
        }
    
        makeMove(direction: .right)
        if movementHappened {
            var fields = getAllFields()
            updateFields(to: &fields, from: fieldsOnPreviousMove)
            return true
        }

        return false
    }
    
    
    func getAllFields() -> [Field] {
        return board.getFieldsInAscendingPosition(filter: {$0.x == $0.x && $0.y == $0.y})
    }
    
    func makeMove(direction: Direction) {
        currenDirectionOfMove = direction
        updateFields(to: &fieldsOnPreviousMove, from: getAllFields())
        switch direction {
        case .up:
            board.getColumns()
                .forEach {mergeValues(in: $0)}
        case.down:
            board.reversedLines(lines: board.getColumns())
                .forEach {mergeValues(in: $0)}
        case.left:
            board.getRows()
                .forEach {mergeValues(in: $0)}
        case.right:
            board.reversedLines(lines: board.getRows())
                .forEach {mergeValues(in: $0)}
        }
        
        if movementHappened {
            generateNewValuesOnBoard()
        }
    }
    
    private func updateFields(to:inout [Field], from: [Field]) {
        to = from.map {$0.value}.map {Field(value: $0)}
    }
    
    /*
     *  Move and merge rules (to the left):
     *  0 2 0 2 -> 4 0 0 0
     *  4 0 2 4 -> 4 2 4 0
     *  2 0 2 2 -> 4 2 0 0
     *  2 2 2 2 -> 4 4 0 0
     *  2 2 4 4 -> 4 8 0 0
     */
   private func mergeValues(in line: [Field]) {
        //step 1: move all non-empty fields to the beginning of the array
        let arr = line.map { $0.value }
        var convertedArr = moveNilToEnd(arr: arr)
        
        
        //step 2: merge
        for index in 1..<arr.count {
            if convertedArr[index] != nil
               && convertedArr[index] == convertedArr[index - 1] {
                convertedArr[index - 1] = convertedArr[index]! * 2
                convertedArr[index] = nil
                convertedArr = moveNilToEnd(arr: convertedArr)
            }
        }
        
        //step 3: update value in fields
        for field in line.enumerated() {
            field.element.value = convertedArr[field.offset]
        }
    }
    
    private func moveNilToEnd(arr: [Int?]) -> [Int?]{
        var filteredArr = arr.filter { $0 != nil }
        let size = filteredArr.count
        for _ in 0..<(arr.count - size) {
            filteredArr.append(nil)
        }
        return filteredArr
    }

}
