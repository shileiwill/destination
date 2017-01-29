package leetcode20.math;
/**
 * 223. Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area
Assume that the total area is never beyond the maximum possible value of int.
 */
public class RectangleArea {

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int areaOfA = (C - A) * (D - B);
        int areaOfB = (G - E) * (H - F);
        
        // Potential Overlap Area
        int left = Math.max(A, E);
        int right = Math.min(C, G);
        int top = Math.min(D, H);
        int bottom = Math.max(B, F);
        
        int overlap = 0;
        if (right > left && top > bottom) {
            overlap = (right - left) * (top - bottom);
        }
        
        return areaOfA + areaOfB - overlap;
    }

}
