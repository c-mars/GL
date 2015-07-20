package mars.c.gl.helpers;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import mars.c.gl.R;
import mars.c.gl.models.*;
import mars.c.gl.models.Object;

/**
 * Created by Constantine Mars on 7/20/15.
 */
public class CSVPolygonReader {
    public static Object read(Context context, int resId) {
        try {
            InputStream inputStream= context.getResources().openRawResource(resId);
            ArrayList<Float> coords;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                coords = new ArrayList<>();
                Integer vertexSize = null;
                Integer verticesCount = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] parts = line.split(",");
                    verticesCount++;
                    if (vertexSize == null) {
                        vertexSize = parts.length;
                    }
                    for (String p : parts) {
                        coords.add(Float.parseFloat(p));
                    }
                }
                Object object = new Object();
                float[] mesh=new float[coords.size()];
                for(int i=0;i<coords.size();i++){
                    mesh[i]=coords.get(i);
                }
                object.setMesh(mesh, vertexSize, verticesCount);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
