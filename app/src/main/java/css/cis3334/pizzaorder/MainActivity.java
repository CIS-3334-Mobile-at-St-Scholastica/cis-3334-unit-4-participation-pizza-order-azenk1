package css.cis3334.pizzaorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements updateViewInterface {


    RadioButton rbSmall;
    RadioButton rbMedium;
    RadioButton rbLarge;
    CheckBox chkbxCheese;
    CheckBox chkbxDelivery;
    TextView txtTotal;
    TextView txtStatus;
    Spinner toppingSpinner;
    PizzaOrderInterface pizzaOrderSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbSmall = (RadioButton) findViewById(R.id.radioButtonSmall);
        rbMedium = (RadioButton) findViewById(R.id.radioButtonMedium);
        rbLarge = (RadioButton) findViewById(R.id.radioButtonLarge);

        chkbxCheese = (CheckBox) findViewById(R.id.checkBoxCheese);
        chkbxDelivery = (CheckBox) findViewById(R.id.checkBoxDeluvery);

        txtTotal = (TextView) findViewById(R.id.textViewTotal);
        txtStatus = (TextView) findViewById(R.id.textViewStatus);

        toppingSpinner = (Spinner)findViewById(R.id.toppingSpinner);
        pizzaOrderSystem = new PizzaOrder(this);

    }

    @Override
    public void updateView(String orderStatus) {
        txtStatus.setText("Order Status" + orderStatus);
    }

    public void onClickOrder(View view) {

        String orderDescription = "";
        String topping = toppingSpinner.getSelectedItem().toString();
        boolean cheese = false;

        if(chkbxCheese.isChecked())
        {
            cheese = true;
        }
        if(rbSmall.isChecked())
        {
             orderDescription = pizzaOrderSystem.OrderPizza(topping, "small", cheese);

        }
        else if(rbMedium.isChecked())
        {
             orderDescription = pizzaOrderSystem.OrderPizza(topping, "medium", cheese);

        }
        else
        {

                 orderDescription = pizzaOrderSystem.OrderPizza(topping, "Large", cheese);


        }



        //display a pop up message for a long period of time
        Toast.makeText(getApplicationContext(), "You have ordered a " +orderDescription , Toast.LENGTH_LONG).show();
        txtTotal.setText("Total Due: " + pizzaOrderSystem.getTotalBill().toString());
    }
}
