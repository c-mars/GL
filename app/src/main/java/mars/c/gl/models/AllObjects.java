package mars.c.gl.models;

import lombok.Data;

/**
 * Created by Constantine Mars on 7/16/15.
 */
@Data
public class AllObjects {
    private final float[] verticesWorld = {
//            table
            0f, 0f,
            9f, 14f,
            0f, 14f,

            0f, 0f,
            9f, 0f,
            9f, 14f,

//            line
            0f, 7f,
            9f, 7f,

//            mallets
            4.5f, 2f,
            4.5f, 12f

    };

    private final float[] verticesGL = {
//            table
            -0.5f, -0.5f,
            0.5f, 0.5f,
            -0.5f, 0.5f,

            -0.5f, -0.5f,
            0.5f, -0.5f,
            0.5f, 0.5f,

//            line
            -0.5f, 0f,
            0.5f, 0f,

//            mallets
            0f, -0.25f,
            0f, 0.25f
    };
}
