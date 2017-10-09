const int buttonPin = 2; 
int buttonState = HIGH;
int lastButtonState = HIGH;
unsigned long lastDebounceTime = 0; 
unsigned long debounceDelay = 50;
int count = 0;
void buttonPressed() {
  lastDebounceTime = millis();
  int reading = digitalRead(buttonPin);
  delay(debounceDelay);
  //if ((millis() - lastDebounceTime) > debounceDelay) 
  {
      if (reading != buttonState) {
        buttonState = reading;
      }
      if (buttonState == HIGH) {
        count +=1;
        Serial.println(count);
      }
  }
  
  lastButtonState = reading;
}

void setup() {
  pinMode(buttonPin, INPUT_PULLUP);

  // Interrupts can happen on "edges" of signals.  
  // Three edge types are supported: CHANGE, RISING, and FALLING 
  attachInterrupt(digitalPinToInterrupt(buttonPin), buttonPressed, CHANGE);
  Serial.begin(9600);
}

void loop() {
    
    delay(500);
}
