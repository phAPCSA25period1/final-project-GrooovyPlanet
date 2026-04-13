public class Event {
        /**
         * Constuctor Variables
         */
        String title;
        String date;

        /**
         * Method for creating  Event object
         */
        Event(String title, String date){
            this.title = title;
            this.date = date;
        }
        /**
         * Method for accessing date within Event object
         */
        public String getDate() {
        return date;
        }
        /**
         * Method for accessing title within Event object
         */
        public String getTitle() {
        return title;
    }
}
