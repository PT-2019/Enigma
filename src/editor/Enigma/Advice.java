package editor.Enigma;

public class Advice {

    private String advice;
    private int duration;

    public Advice(){
        this.duration = 2;
    }

    public Advice(int duration){
        this.duration = duration;
    }

    public Advice(String advice){
        this.advice = advice;
        this.duration = 2;
    }

    public Advice(String advice, int duration){
        this.advice = advice;
        this.duration = duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration * 60000;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getAdvice() {
        return advice;
    }
}
