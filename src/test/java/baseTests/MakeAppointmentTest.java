package baseTests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AppointmentMakePage;
import pages.ConfirmationAppointmentPage;
import pages.HomePage;

public class MakeAppointmentTest extends BaseTest{

    @Test(priority = 1, dataProvider = "Data")
    public void MakeAppointment(String Facility, Integer BtnRadioValue,String Date, String Comment){

          AppointmentMakePage appointmentPage= homePage.clickOnBtnLogin();
          //verify if the user is on the Make appointment Page - screen title must be Make appointment
          Assert.assertEquals(appointmentPage.getTextOnScreen(),"Make Appointment");
          System.out.println("The user has successfully logged in and moved to the " + appointmentPage.getTextOnScreen() + "Page");
          appointmentPage.enterFacility(Facility);
          appointmentPage.clickOnCheckBoxApply();
          appointmentPage.selectHealthProgramOption(BtnRadioValue);
          appointmentPage.enterDate(Date);
          appointmentPage.enterComments(Comment);
          //user clicks on the BookAppointment button
          ConfirmationAppointmentPage confirmationAppointmentPage = appointmentPage.clickOnBtnBookAppointment();
          //Assert if the Facility input detail displayed on the confirmation Appointment Page is the same as the one entered on the Make Appointment Page
          Assert.assertTrue(confirmationAppointmentPage.getFacilityDetailSelected().contains(Facility));
          //Printout the Facility detail entered
          System.out.println("Facility : " +confirmationAppointmentPage.getFacilityDetailSelected());

          //Compare the Date details and printout the date you have selected
          Assert.assertTrue(confirmationAppointmentPage.getVisitDateDetail().contains(Date));
          System.out.println("Visit Date: " +confirmationAppointmentPage.getVisitDateDetail());

          Assert.assertTrue(confirmationAppointmentPage.getCommentDetail().contains(Comment));
          System.out.println("Your Comment: " +confirmationAppointmentPage.getCommentDetail());
          //AppointmentMakePage appointmentMakePage = confirmationAppointmentPage.clickOnGoToHomepageBtn();


    }

    @DataProvider()
    public Object [][] Data(){
        Object[][] data =new Object[3][4];
        data [0][0]="Hongkong CURA Healthcare Center";data[0][1]= 1;data[0][2]="21/12/2022";data[0][3]="TESTING TESTING THANKS LOUISE";
        data [1][0]="Tokyo CURA Healthcare Center";data[1][1]= 2;data[1][2]="12/12/2022";data[1][3]="TESTING TESTING 2 THANKS DAVID";
        data [2][0]="Seoul CURA Healthcare Center";data[2][1]= 0;data[2][2]="20/12/202";data[2][3]="I PASS MY EXAM...THANK YOU DAVID...THANK YOU LOUISE";
        return data;

    }
}
