package backend;

import java.io.Serializable;

public enum FarbEnum implements Serializable{
	schwarz {
		@Override
		public String toString() {
			
			return "schwarz";
		}
	}, weiss {
		@Override
		public String toString() {
			
			return "weiss";
		}
	}, nichts;
	
}
