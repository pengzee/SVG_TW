package work.pengzhe.com.svg_tw;

import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    private TaiWan taiWan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taiWan = (TaiWan) findViewById(R.id.taiwan);
        ParseSVG();
    }

    private void ParseSVG() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            List<City> cities = new ArrayList<>();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream is = getResources().openRawResource(R.raw.taiwanhigh);
            Document document = builder.parse(is);
            NodeList svgNodeList = document.getElementsByTagName("path");
            for (int i = 0; i < svgNodeList.getLength(); i++) {
                Element element = (Element) svgNodeList.item(i);
                String path = element.getAttribute("android:pathData");
                City city = new City(this);
                Path path_svg = PathParser.createPathFromPathData(path);
                city.setPath(path_svg);
                RectF rectF = new RectF();
                path_svg.computeBounds(rectF, true);
                Region region = new Region();
                region.setPath(path_svg, new Region((int) (rectF.left), (int) (rectF.top), (int) (rectF.right), (int) (rectF.bottom)));
                city.setRegion(region);
                cities.add(city);
            }
            taiWan.setCities(cities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
