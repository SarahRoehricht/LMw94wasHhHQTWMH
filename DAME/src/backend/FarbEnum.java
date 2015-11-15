package backend;

import java.io.Serializable;

public enum FarbEnum implements Serializable{
	schwarz {
		@Override
		public String toString() {
			
			return "Schwarz";
		}
	}, weiss {
		@Override
		public String toString() {
			
			return "Weiss";
		}
	}, nichts;
	
}
