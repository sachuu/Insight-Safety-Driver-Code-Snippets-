//Declare pin functions on Redboard
#define stp 8
#define dir 9
#define MS1 4
#define MS2 5
#define EN  6

//Declare variables for functions
char user_input;
int x;
int y;
int state;

void setup() {
  pinMode(stp, OUTPUT);
  pinMode(dir, OUTPUT);
  pinMode(MS1, OUTPUT);
  pinMode(MS2, OUTPUT);
  pinMode(EN, OUTPUT);
  resetEDPins(); //Set step, direction, microstep and enable pins to default states
  Serial.begin(9600); //Open Serial connection for debugging
  Serial.println("Begin motor control");
  Serial.println();
  //Print function list for user selection
  Serial.println("Enter number for control option:");
  Serial.println("1. Turn at default microstep mode.");
  Serial.println("2. Reverse direction at default microstep mode.");
  Serial.println("3. Turn at 1/8th microstep mode.");
  Serial.println("4. Step forward and reverse directions.");
  Serial.println();
}

 //Main loop
void loop() {
  while(Serial.available()){
      user_input = Serial.read(); //Read user input and trigger appropriate function
      digitalWrite(EN, LOW); //Pull enable pin low to allow motor control
      StepForwardDefault();
      resetEDPins();
      Serial.print("The code is working");
  }
}

//Default microstep mode function
void StepForwardDefault(){
  Serial.println("Moving forward at default step mode.");
  digitalWrite(dir, LOW); //Pull direction pin low to move "forward"
  for(x= 1; x<1000; x++)  //Loop the forward stepping enough times for motion to be visible
  {
    digitalWrite(stp,HIGH); //Trigger one step forward
    delay(1);
    digitalWrite(stp,LOW); //Pull step pin low so it can be triggered again
    delay(1);
  }
}

//Reverse default microstep mode function
void ReverseStepDefault()
{
  Serial.println("Moving in reverse at default step mode.");
  digitalWrite(dir, HIGH); //Pull direction pin high to move in "reverse"
  for(x= 1; x<1000; x++)  //Loop the stepping enough times for motion to be visible
  {
    digitalWrite(stp,HIGH); //Trigger one step
    delay(1);
    digitalWrite(stp,LOW); //Pull step pin low so it can be triggered again
    delay(1);
  }
  Serial.println("Enter new option");
  Serial.println();
}

//Reset Easy Driver pins to default states
void resetEDPins()
{
  digitalWrite(stp, LOW);
  digitalWrite(dir, LOW);
  digitalWrite(MS1, LOW);
  digitalWrite(MS2, LOW);
  digitalWrite(EN, HIGH);
}
------------------------------------------
int Distance = 0; 
int BIGB = 0;
int SPP = 1000;
int SVP = 2500;
int SPEEDA = 1;
int SPEEDB = 0;
int XPP = 25;

void setup() {       
  pinMode(8, OUTPUT);     
  pinMode(9, OUTPUT);
  digitalWrite(8, LOW);
  digitalWrite(9, LOW);
}

void loop() {
  digitalWrite(9, HIGH);
  delay(SPEEDA);          
  digitalWrite(9, LOW); 
  delay(SPEEDB);
  Distance = Distance + 10;   
  
  if (Distance == SVP)
  {
    Distance = 0;
    BIGB = BIGB +1;
    delay(SPP);
  if (BIGB == XPP)
  {
    if (digitalRead(8) == LOW)
    {
      digitalWrite(8, HIGH);
      BIGB = 0;
      SPP = 0;
      SVP = 1000;
      SPEEDA = 0;
      SPEEDB = 1;
      XPP = 80;
    }
    else
    {
      digitalWrite(8, LOW);
      exit(0);  //The 0 is required to prevent compile error.
    }
    }

    delay(0);
    }         
}
