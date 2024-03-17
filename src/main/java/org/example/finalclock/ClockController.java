package org.example.finalclock;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.util.Duration;

public class ClockController {
    @FXML
    private Label timeLabel;
    @FXML
    private ToggleButton alarmButton;
    @FXML
    private Label alarmLabel;
    private boolean isAlarmOn;
    private boolean isAlarmToggled;
    private boolean isHourClicked;
    private boolean isMinuteClicked;
    private boolean isResetClicked;
    private boolean isAlarmClicked;
    private boolean isStop;
    private int countHourClicks = 0;
    private int countMinuteClicks = 0;
    private int countAlarmMinuteClicks = 0;
    private int countAlarmHourClicks = 0;
    private Time timeChangeable;
    private Time alarmTime;
    @FXML
    private Button stopRinging;
    @FXML
    protected void onStopRingingButtonClick() {
        isStop = true;
    }
    @FXML
    protected void onAlarmButtonClick() {
        if (alarmButton.isSelected()) {
            isAlarmOn = true;
            isAlarmClicked = true;
            isAlarmToggled = true;
        } else {
            isAlarmToggled = false;
        }
    }
    @FXML
    protected void onHourButtonClick() {
        isHourClicked = true;
    }
    @FXML
    protected void onMinuteButtonClick() {
        isMinuteClicked = true;
    }
    @FXML
    protected void onResetButtonClick() { isResetClicked = true;}
    public void initialize() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), this::updateTime));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    private void updateTime(ActionEvent e) {
        if (isAlarmClicked) {
            isAlarmClicked = false;
            countAlarmHourClicks = 0;
            countAlarmMinuteClicks = 0;
            countHourClicks = 0;
            countMinuteClicks = 0;
        }
        if (isAlarmToggled) {
            alarmTime = new Time(true);
            alarmTime.incrementHours(countAlarmHourClicks);
            alarmTime.incrementMinutes(countAlarmMinuteClicks);
            if (isHourClicked) {
                countAlarmHourClicks++;
                if (countAlarmHourClicks % 24 == 0) countAlarmHourClicks = 0;
                isHourClicked = false;
            }
            if (isMinuteClicked) {
                countAlarmMinuteClicks++;
                if (countAlarmMinuteClicks % 60 == 0) {
                    countAlarmMinuteClicks = 0;
                    countAlarmHourClicks++;
                }
                isMinuteClicked = false;
            }
            if (isResetClicked) {
                countAlarmMinuteClicks = 0;
                countAlarmHourClicks = 0;
                isResetClicked = false;
            }
            timeLabel.setText(alarmTime.getCurrentTime());
        } else {
            timeChangeable = new Time();
            timeChangeable.incrementHours(countHourClicks);
            timeChangeable.incrementMinutes(countMinuteClicks);

            timeLabel.setText(timeChangeable.getCurrentTime());

            if (isHourClicked) {
                countHourClicks++;
                if (countHourClicks % 24 == 0) countHourClicks = 0;
                isHourClicked = false;
            }
            if (isMinuteClicked) {
                countMinuteClicks++;
                if (countMinuteClicks % 60 == 0) {
                    countMinuteClicks = 0;
                    countHourClicks++;
                }
                isMinuteClicked = false;
            }
            if (isResetClicked) {
                countHourClicks = 0;
                countMinuteClicks = 0;
                isResetClicked = false;
            }
        }
        stopRinging.setDisable(true);
        if (isStop) {
            alarmTime = null;
            isStop = false;
        }
        if (alarmTime != null) {
            alarmLabel.setText("Alarm will ring in:\n"+alarmTime.getCurrentTime());
        } else {
            alarmLabel.setText("Alarm is OFF");
        }
        if (alarmTime != null
            && !isAlarmToggled
            && (timeChangeable.getHours() == alarmTime.getHours())
            && (timeChangeable.getMinutes() == alarmTime.getMinutes())
            && isAlarmOn) {
                stopRinging.setDisable(false);
                System.out.println(alarmTime.toString() + " al = loc "+ timeChangeable.toString());
                alarmLabel.setText("Alarm is RINGING");
        }

    }
}