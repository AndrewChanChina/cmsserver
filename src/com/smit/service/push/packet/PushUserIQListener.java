package com.smit.service.push.packet;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;

public class PushUserIQListener implements PacketListener {

	public void processPacket(Packet packet) {
		System.out.print("packet.toXML()=" + packet.toXML());
		
		if (packet instanceof PushUserIQ) {
			
		}
	}

}
