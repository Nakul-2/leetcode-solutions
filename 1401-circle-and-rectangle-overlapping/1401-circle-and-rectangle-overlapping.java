class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int X , Y;
        if(xCenter < x1){
            X = x1;
        }else if(xCenter > x2){
            X = x2;
        }else{
            X = xCenter;
        }

        if(yCenter < y1){
            Y = y1;
        }else if(yCenter > y2){
            Y = y2;
        }else{
            Y = yCenter;
        }

        double dist = Math.sqrt((xCenter - X)*(xCenter - X) + (yCenter - Y)*(yCenter - Y));
        return dist <= radius;
    }
}