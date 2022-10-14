package root.Entities;

import lombok.Data;
import java.time.Instant;

@Data
public class RobotDataEO {

    private String id;
    private String groupId;
    private String accelX;
    private String accelY;
    private String accelZ;
    private String accelVert;
    private String oriPitch;
    private String oriRoll;
    private String oriYaw;
    private String gyroPitch;
    private String gyroRoll;
    private String gyroYaw;
    private String veloX;
    private String veloY;
    private String locX;
    private String locY;
    private String spd;
    private String travelDist;


    public String toString(){
        return id+","+groupId+","+ accelX + "," + accelY + "," + accelY + "," + accelZ + "," + accelVert + "," + oriPitch + "," + oriRoll + "," + oriYaw + "," + gyroPitch + "," + gyroRoll + "," + gyroYaw + "," + veloX + "," + veloY + "," + locX + "," + locY + "," + spd + "," + travelDist;
    }
}
