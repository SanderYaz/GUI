package com.example.tictactoemvp;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BoardPresenter implements BoardListener {

    private BoardView boardView;
    private Board board;
    private List<CellClickListener> cellClickListeners = new ArrayList<>();

    public BoardPresenter(BoardView view) {
        this.boardView = view;
        board = new Board(this);
    }

    private void move(byte row, byte col) {
        board.move(row, col);
    }

    public void addCellClickListener(CellClickListener listener) {
        cellClickListeners.add(listener);
    }

    @Override
    public void playedAt(byte player, byte row, byte col) {
        if (player == BoardListener.PLAYER_1) {
            boardView.putSymbol(BoardView.PLAYER_1_SYMBOL, row, col);
        } else if (player == BoardListener.PLAYER_2) {
            boardView.putSymbol(BoardView.PLAYER_2_SYMBOL, row, col);
        }
    }

    @Override
    public void gameEnded(byte winner) {
        switch (winner) {
            case BoardListener.NO_ONE:
                boardView.gameEnded(BoardView.DRAW);
            case BoardListener.PLAYER_1:
                boardView.gameEnded(BoardView.PLAYER_1_WINNER);
            case BoardListener.PLAYER_2:
                boardView.gameEnded(BoardView.PLAYER_2_WINNER);
        }
    }

    static class CellClickListener implements View.OnClickListener {
        BoardPresenter presenter;
        byte row;
        byte col;

        public CellClickListener(BoardPresenter presenter, byte row, byte col) {
            this.row = row;
            this.col = col;
            this.presenter = presenter;
        }

        @Override
        public void onClick(View view) {
            Log.d("CellClickListener", "at" + row + ", " + col);
            presenter.move(row, col);
        }
    }
}