//
//  Field.swift
//  Game2048
//
//  Created by Дмитрий Мелешин on 11.12.2022.
//

import Foundation

class Field: NSCopying {

    var value: Int?
    
    var isEmpty: Bool {
        return value == nil
    }
    
    init(value: Int? = nil) {
        self.value = value
    }
    
    func copy(with zone: NSZone? = nil) -> Any {
        let field = Field(value: self.value)
        return field
    }
    
}


extension Field: Equatable{
    
    static func == (lhs: Field, rhs: Field) -> Bool {
        return lhs.value == rhs.value
    }
    
}

