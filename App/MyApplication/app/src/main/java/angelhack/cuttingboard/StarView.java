package angelhack.cuttingboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by William Zulueta on 7/16/17.
 */

public class StarView extends View
{
    private Paint _paint;
    private Paint _backgroundPaint;
    public StarView(Context context)
    {
        super(context);
        init();
    }

    public StarView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        init();
    }

    private void init()
    {
        _paint = new Paint();
        _paint.setColor(Color.YELLOW);

        _backgroundPaint = new Paint();
        _backgroundPaint.setColor(getResources().getColor(R.color.backgroundColor));
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        canvas.drawRect(0, 0, getWidth(), getHeight(), _backgroundPaint);
        drawStars(canvas);
    }

    private void drawStars(Canvas canvas)
    {
        canvas.drawPath(createStar(100, 100), _paint);
    }

    private Path createStar(int width, int height)
    {
        Path path = new Path();
        path.moveTo(width / 2, 0);
        path.lineTo(width / 4, height / 4);
        path.lineTo(0, height / 4);
        path.lineTo(width / 4, height / 2);
        path.lineTo(0, height);
        path.lineTo(width / 2, (int) (height * .75));
        path.lineTo(width, height);
        path.lineTo((int) (width * .75), height / 2);
        path.lineTo(width, height / 2);
        path.lineTo((int) (width * .75), height / 4);
        path.lineTo(width / 2, 0);
        return path;
    }
}
