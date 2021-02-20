package com.bhuvesh.medicalbook.yogainstructorfeature;

import com.bhuvesh.medicalbook.R;

public class Yoga {
    /*This class holds data related Yoga
    Such as Yoga name and procedure.

    The data has been carefully selected from
    https://yogainternational.com/article/view/a-step-by-step-home-practice1

    The credit goes to Author: Trish O'Reilly and website https://yogainternational.com/.
    */
    
    // declare variables to be used by class
    private String yogaName;
    private String yogaDetail;
    private int imageId;


    // initialize the array containing Yoga class objects
    public static final Yoga[] yogas = {
            new Yoga("Tadasana - Arm Raises","1) Stand with your feet parallel, hip-distance apart, and your arms relaxed at your sides. Take a few breaths before proceeding.\n" +
                    "\n" +
                    "2) Inhaling, raise your arms to the front and over your head. Pause at the top.\n" +
                    "\n" +
                    "3) Exhaling, lower your arms"+
                    "\n" + "\n"+
                    "Suggestion: Use timer for assistance."+"\n" + "\n", R.drawable.tadasana),

            new Yoga("Uttanasana - Standing forwards bend",
                    "1) Stand with your feet parallel, hip-distance apart, " +
                    "and your arms at your sides. Take a few breaths before proceeding.\n" +
                    "\n" +
                    "2) Inhaling, raise your arms to the front " +
                    "and then over your head, keeping your elbows relaxed.\n" +
                    "\n" +
                    "3) Exhaling, fold forward, slightly bending the knees, " +
                    "taking the arms to the side and resting the hands on the small of the back."+ "\n" + "\n"+
                            "Suggestion: Use timer for assistance."+"\n" + "\n", R.drawable.uttanasana),

            new Yoga("Virbhadrasana - Kneeling Warrior","1) Kneel, placing padding under the right knee. " +
                    "Step your left foot forward. Placing the hands at the center of the chest, take a few breaths in place."+ "\n"
                     +"\n" +"2) Inhaling, bend your left knee while opening the arms and lifting the chest."+ "\n"
                    +"\n"+"3) Exhaling, bring the hands back to the chest as you straighten the left knee and return to the " +
                    "starting position."+"\n" + "\n"+
                    "Suggestion: Use timer for assistance."+"\n" + "\n", R.drawable.virbhadrasana),

            new Yoga("Dvipada Pitham - Supine Back Arch","1) Lie on your back with your feet hip-distance " +
                    "apart on the floor and your arms comfortably at your sides, palms down. Fully releasing the back of the body into the floor.\n" +
                    "\n" +"2) Inhaling, press through the feet and raise the hips off the floor, while lifting the arms over your " +
                    "head to the floor behind you. Keep the elbows slightly bent and all parts of each foot firmly pressing into the floor.\n" +
                    "\n" +"3) Exhaling, lower the hips and arms to the starting position.\n" +
                    "\n" +"4) Then take the pose, grabbing the ankles or placing the palms together behind your back with the " +
                    "fingers interlaced, and stay for six breaths."+"\n" + "\n"+
                    "Suggestion: Use timer for assistance."+"\n" + "\n", R.drawable.dvipada),

            new Yoga("Bhujangasana Vinyasa - Prone Back Arch Sequence","1) Lie on your stomach and place your " +
                    "forehead on the floor and your hands under your shoulders. Separate your ankles a foot apart and relax your legs.\n" +
                    "\n" +"2) Inhaling, lift the chest, shoulders, and head, without pushing down on the hands.\n" +
                    "\n" +"3) Exhaling, bend the knees, lifting the feet and drawing the heels in toward the buttocks.\n" +
                    "\n" +"4) Inhaling, lower the feet back to the floor.\n" +
                    "\n" +"5) Exhaling, lower the chest and forehead to the floor."+
                    "\n" + "\n"+
                    "Suggestion: Use timer for assistance."+"\n" + "\n", R.drawable.bhujangasana),

            new Yoga("Dhanurasana - Bow posture","1) Keeping the forehead on the floor, lift your feet and reach back and take hold of your ankles.\n" +
                    "\n" + "\n" + "2) Inhaling, lift the chest, shoulders, head, feet, and thighs.\n" +
                    "\n" +"\n" +"3) Exhaling, release the posture.\n" +
                    "\n" +"4) Repeat six times, linking your breath and movement.\n" +
                    "\n" +"5) Then stay in the posture for six breaths."+"\n" + "\n"+
                    "Suggestion: Use timer for assistance."+"\n" + "\n", R.drawable.dhanurasana),

            };

    // getter and setter function of class to interact with the class objects
    public Yoga(String yogaName, String yogaDetail, int Id) {
        this.yogaName = yogaName;
        this.yogaDetail = yogaDetail;
        this.imageId = Id;
    }


    public String getYogaName() {
        return yogaName;
    }

    public String getYogaDetail() {
        return yogaDetail;
    }

    public int getImageId() {
        return imageId;
    }

    @Override
    public String toString() {
        return "Yoga{" +
                "yogaName='" + yogaName + '\'' +
                '}';
    }
}