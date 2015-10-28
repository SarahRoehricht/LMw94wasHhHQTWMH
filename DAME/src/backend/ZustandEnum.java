package backend;

public enum ZustandEnum {
	nichts, SStein {
		@Override
		public String toString() {

			return "Stein";
		}
	}

	,
	WStein {
		@Override
		public String toString() {

			return "Stein";
		}
	},
	SDame {

		@Override
		public String toString() {

			return "Dame";
		}
	},
	WDame {
		@Override
		public String toString() {

			return "Dame";
		};
	}
}
