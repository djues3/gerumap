module gerumap.tim.daviddjuretanovic.dusanobradovic {
    requires static lombok;
    requires java.desktop;
    requires com.google.gson;
    opens raf.dsw.gerumap.app.mapRepository.model to com.google.gson;
    opens raf.dsw.gerumap.app.mapRepository to com.google.gson;
    opens raf.dsw.gerumap.app.mapRepository.model.elements to com.google.gson;
}

