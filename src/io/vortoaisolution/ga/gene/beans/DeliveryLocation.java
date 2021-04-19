package io.vortoaisolution.ga.gene.beans;

public class DeliveryLocation {
    private int locID;
    private double xPos;
    private double yPos;

    public DeliveryLocation(int locID, double xPos, double yPos) {
        this.locID= locID;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public double [] getLocation(){
        return new double[]{this.xPos, this.yPos};
    }

    public int getLocID() {
        return locID;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public double distanceFrom(DeliveryLocation destination) {
        return DeliveryLocation.distanceBetweenTwoPoints(this, destination);
    }

    public static double distanceBetweenTwoPoints(DeliveryLocation first, DeliveryLocation second){
        if (first != null && second != null){

            return Math.sqrt(
                    Math.pow(first.getxPos()- second.getxPos(),2) +
                            Math.pow(first.getyPos()- second.getyPos(),2));
        } else {
            return -1;
        }
    }

}
