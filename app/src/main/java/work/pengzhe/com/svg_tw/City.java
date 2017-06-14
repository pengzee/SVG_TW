package work.pengzhe.com.svg_tw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;

/**
 * Created on 2017/6/1 15:24
 *
 * @author PengZee
 */

public class City {

    private Context context;
    private Path path;
    private Paint paint;
    private boolean isTouch;
    private Region region;

    public City(Context context) {
        this.context = context;
        paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
    }

    public boolean isTouch() {
        return isTouch;
    }

    public void setTouch(boolean touch) {
        isTouch = touch;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public void draw(Canvas canvas) {
        if (isTouch()) {
            int color = getRanColor();
            paint.setColor(color);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setShadowLayer(8, 3, 3, Color.DKGRAY);

        } else {
            paint.setColor(this.context.getResources().getColor(R.color.color1));
            paint.setStyle(Paint.Style.STROKE);
        }
        canvas.drawPath(path, paint);
    }

    private int getRanColor() {
        int[] colors = {this.context.getResources().getColor(R.color.color2),
                this.context.getResources().getColor(R.color.color3), this.context.getResources().getColor(R.color.color4)};
        return colors[(int) (Math.random() * 3)];
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
