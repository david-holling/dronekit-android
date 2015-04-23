// MESSAGE NAMED_VALUE_FLOAT PACKING
package com.MAVLink.common;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
        
/**
* Send a key-value pair as float. The use of this message is discouraged for normal packets, but a quite efficient way for testing new messages and getting experimental debug output.
*/
public class msg_named_value_float extends MAVLinkMessage{

    public static final int MAVLINK_MSG_ID_NAMED_VALUE_FLOAT = 251;
    public static final int MAVLINK_MSG_LENGTH = 18;
    private static final long serialVersionUID = MAVLINK_MSG_ID_NAMED_VALUE_FLOAT;


     	
    /**
    * Timestamp (milliseconds since system boot)
    */
    public int time_boot_ms;
     	
    /**
    * Floating point value
    */
    public float value;
     	
    /**
    * Name of the debug variable
    */
    public byte name[] = new byte[10];
    

    /**
    * Generates the payload for a mavlink message for a message of this type
    * @return
    */
    public MAVLinkPacket pack(){
        MAVLinkPacket packet = new MAVLinkPacket();
        packet.len = MAVLINK_MSG_LENGTH;
        packet.sysid = 255;
        packet.compid = 190;
        packet.msgid = MAVLINK_MSG_ID_NAMED_VALUE_FLOAT;
        		packet.payload.putInt(time_boot_ms);
        		packet.payload.putFloat(value);
        		
        for (int i = 0; i < name.length; i++) {
            packet.payload.putByte(name[i]);
        }
                    
        
        return packet;
    }

    /**
    * Decode a named_value_float message into this class fields
    *
    * @param payload The message to decode
    */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
        	    
        this.time_boot_ms = payload.getInt();
        	    
        this.value = payload.getFloat();
        	    
         
        for (int i = 0; i < this.name.length; i++) {
            this.name[i] = payload.getByte();
        }
                
        
    }

    /**
    * Constructor for a new message, just initializes the msgid
    */
    public msg_named_value_float(){
        msgid = MAVLINK_MSG_ID_NAMED_VALUE_FLOAT;
    }

    /**
    * Constructor for a new message, initializes the message with the payload
    * from a mavlink packet
    *
    */
    public msg_named_value_float(MAVLinkPacket mavLinkPacket){
        this.sysid = mavLinkPacket.sysid;
        this.compid = mavLinkPacket.compid;
        this.msgid = MAVLINK_MSG_ID_NAMED_VALUE_FLOAT;
        unpack(mavLinkPacket.payload);        
    }

         
    /**
    * Sets the buffer of this message with a string, adds the necessary padding
    */
    public void setName(String str) {
        int len = Math.min(str.length(), 10);
        for (int i=0; i<len; i++) {
            name[i] = (byte) str.charAt(i);
        }

        for (int i=len; i<10; i++) {			// padding for the rest of the buffer
            name[i] = 0;
        }
    }

    /**
    * Gets the message, formated as a string
    */
    public String getName() {
        String result = "";
        for (int i = 0; i < 10; i++) {
            if (name[i] != 0)
                result = result + (char) name[i];
            else
                break;
        }
        return result;

    }
                         
    /**
    * Returns a string with the MSG name and data
    */
    public String toString(){
        return "MAVLINK_MSG_ID_NAMED_VALUE_FLOAT -"+" time_boot_ms:"+time_boot_ms+" value:"+value+" name:"+name+"";
    }
}
        