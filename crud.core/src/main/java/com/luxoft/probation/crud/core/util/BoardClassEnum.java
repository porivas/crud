package com.luxoft.probation.crud.core.util;


/**
 * Created by hhayryan on 5/30/2016.
 */
public enum BoardClassEnum {

    BOARD_CLASS_1(1),
    BOARD_CLASS_2(2);

    private int boardClass;

    BoardClassEnum(int boardClass) {
        this.boardClass = boardClass;
    }

    public int getBoardClass() {
        return boardClass;
    }

    public static int[] getBoardClasses() {
        return new int[]{
                BOARD_CLASS_1.getBoardClass(),
                BOARD_CLASS_2.getBoardClass()
        };
    }

    public static BoardClassEnum fromInt(int boardClass) throws IllegalArgumentException {
        for (BoardClassEnum type : BoardClassEnum.values()) {
            if (boardClass == type.boardClass) {
                return type;
            }
        }

        throw new IllegalArgumentException(String.format("Provided board glass argument not valid: {%d}, it should be 1 or 2", boardClass));
    }
}
