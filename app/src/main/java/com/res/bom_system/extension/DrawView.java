package com.res.bom_system.extension;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View {
    private enum Row{
        Top(50),
        Mid(375),
        Bot(800);

        public final int pos;

        Row(int pos){
            this.pos = pos;
        }
    }

    Paint paint = new Paint();
    final private int sizeRec = 85;



    public DrawView(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
/*
        int col = 0;

        // create left wing
        while(col < 5){
            createRestaurant(canvas, Row.Bot, col);
            col++;
        }

        // skip some colums for spacing
        col += 3;

        int tables = col +5;

        while(col < tables){
            createRestaurant(canvas, Row.Bot, col);
            createRestaurant(canvas, Row.Top, col);
            if(col == tables - 1)
                createRestaurant(canvas, Row.Mid, col);
            col++;
        }
        */

    }

    private void createRestaurant(Canvas canvas, Row r, int colID){

/*
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRect(30, 30, 80, 80, paint);
        paint.setStrokeWidth(0);
        paint.setColor(Color.CYAN);
        canvas.drawRect(33, 60, 77, 77, paint );
        paint.setColor(Color.YELLOW);
        canvas.drawRect(33, 33, 77, 60, paint );

        */


        int left = (colID+1) * 105;

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);
        canvas.drawRect(left, r.pos, left + sizeRec, r.pos + sizeRec, paint);
        paint.setStrokeWidth(0);
        paint.setColor(Color.parseColor("#B6DB49"));
        canvas.drawRect(left + 3, r.pos + 30, (left + sizeRec) -3, (r.pos + sizeRec) - 3, paint );
        paint.setColor(Color.parseColor("#FF7979"));
        canvas.drawRect(left + 3, r.pos +3, (left + sizeRec) - 3, (r.pos + sizeRec) - 20, paint );



    }



}
