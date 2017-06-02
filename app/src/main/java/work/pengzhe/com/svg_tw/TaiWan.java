package work.pengzhe.com.svg_tw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

/**
 * Created on 2017/6/1 15:26
 *
 * @author PengZee
 */

public class TaiWan extends View {

    List<City> cities;

    public TaiWan(Context context) {
        super(context);
    }

    public TaiWan(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (null != cities && cities.size() > 0) {
            canvas.scale(1.2f, 1.2f);
            for (City city : cities) {
                city.draw(canvas);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
                for (City city : cities) {
                    Region region = city.getRegion();
                    boolean isContain = region.contains((int) (x / 1.2f), (int) (y / 1.2f));
                    if (isContain) {
                        city.setTouch(true);
                    } else {
                        city.setTouch(false);
                    }
                }
                invalidate();
        }
        return true;
    }


    public List<City> getCities() {
        return cities;

    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
