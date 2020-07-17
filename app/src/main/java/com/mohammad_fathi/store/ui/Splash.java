package com.mohammad_fathi.store.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.mohammad_fathi.store.R;
import com.mohammad_fathi.store.model.room.entity.Discount_Items_entity;
import com.mohammad_fathi.store.model.room.entity.Products;
import com.mohammad_fathi.store.model.webservice.WebServiceAPI;
import com.mohammad_fathi.store.viewmodel.Items_ViewModel;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Splash extends AppCompatActivity {

    private final int splash_time = 2500;
    Items_ViewModel items_viewModel;

    Call<ArrayList<Products>> productList;
    Call<ArrayList<Discount_Items_entity>> discountItemList;

    boolean doubleBackToExitPressedOnce =false;

    int flag = 0;
    int connection;
    List list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash2);

        items_viewModel = ViewModelProviders.of(this).get(Items_ViewModel.class);


        if (!isNetworkConnected()) {
            Toast.makeText(this, R.string.noInternet, Toast.LENGTH_LONG).show();
            Thread t = new Thread(){
                public void run(){
                    try {
                        Thread.sleep(5000);
                        Intent i = new Intent(Splash.this, Splash.class);
                        startActivity(i);
                        finish();
                    }catch (Exception e){}
                }
            };
            t.start();

        }
        if (isNetworkConnected()){

            //--------------------Get Data from WebServer -------------------------
            webService_GET_Products();
            webService_GET_Discount();

            // SELECT Method for TEST --------------------------
            getAllProducts();
            selectAll_Product();
            selectAll_DiscountItems();

            //INSERT Method for TEST (LOCAL) ----------------------------
            if (selectAll_Product() == null)
                getAllProducts();
            if (selectAll_DiscountItems() == null)
                getAllDiscountItems_Local();


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    Splash.this.finish();
                }
            }, splash_time);

        }
    }




    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.back_again_to_exit, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    private void getAllProducts() {
        //getData();


        items_viewModel.insertInto_productsTable_local(new Products("لپ تاپ 17 اینچی ایسوس مدل ROG GL702VS - B", "ASUS ROG GL702VS - B - 17 inch Laptop", "Laptop", 5850000, "ASUS", "https://itmag.ua/upload/iblock/cd2/142.png"));
        items_viewModel.insertInto_productsTable_local(new Products("لپ تاپ 15 اینچی ایسوس مدل VivoBook K570UD - J", "ASUS VivoBook K570UD - J- 15 inch Laptop", "Laptop", 5850000, "ASUS", "https://www.asus.com/media/global/products/8PctRO0ZYUiRrvG9/P_setting_xxx_0_90_end_300.png"));
        items_viewModel.insertInto_productsTable_local(new Products("لپ تاپ 15 اینچی اچ پی مدل Envy X360 15T BP100 WP - B", "HP Envy X360 15T BP100 WP - B - 15 inch Laptop", "Laptop", 5850000, "HP", "https://hp-iranco.com/wp-content/uploads/2018/06/HP-Envy-X360-15T-BP100-C-15-inch-Laptop-6.png"));
        items_viewModel.insertInto_productsTable_local(new Products("لپ تاپ 15 اینچی لنوو مدل Ideapad 320 - AT", "Lenovo Ideapad 320 - AT - 15 inch Laptop", "Laptop", 5850000, "Lenovo", "https://www.lenovo.com/medias/series-l340-400x300.png?context=bWFzdGVyfHJvb3R8OTcwMTl8aW1hZ2UvcG5nfGhhNS9oNzMvOTk0MjgwODgyMTc5MC5wbmd8MGMxM2ViNTJjMDYyMmNkOGNmMTNmOGM1MTM1ZjBiNDZjNWY4OTZjYTUwYTljNDYyYzM3MGE0YTc3NmIwYmI0ZA&w=480"));
        items_viewModel.insertInto_productsTable_local(new Products("لپ تاپ 14 اینچی دل مدل Inspiron 3476", "DELL Inspiron 3476 14inch Laptop", "Laptop", 5850000, "Dell", "https://egyptlaptop.com/images/watermarked/1/thumbnails/550/450/detailed/18/Dell_Inspiron-3476-Intel_Core_I7-EGYPTLAPTOP-1.jpg.png"));
        items_viewModel.insertInto_productsTable_local(new Products("لپ تاپ ايسوس ASUS ROG GX800VH", "Laptop Asus ASUS ROG GX800VH", "Laptop", 5850000, "ASUS", "https://remcentr.moscow/wp-content/uploads/2018/12/ROG-G752VS.png"));


        items_viewModel.insertInto_productsTable_local(new Products("نرم افزار آموزشی کتیا Catia V5 R20 سطح پیشرفته ", "Catia V5 R20", "Tutorial", 450000, "Vista", "http://www.yaaremehraban.com/230-large_default/%D8%A2%D9%85%D9%88%D8%B2%D8%B4-%D8%AC%D8%A7%D9%85%D8%B9-%DA%A9%D8%AA%DB%8C%D8%A7-part-2-catia.jpg"));
        items_viewModel.insertInto_productsTable_local(new Products("نرم افزار آموزش ساخت چندرسانه ای (مالتی مدیا و تعاملی) نشر نوآوران", "Noavaran Captivate Training software", "Tutorial", 450000, "Noavaran", "https://dkstatics-public.digikala.com/digikala-products/1177959.jpg?x-oss-process=image/resize,m_lfit,h_600,w_600/quality,q_80"));
        items_viewModel.insertInto_productsTable_local(new Products("نرم افزار آموزش Corel Draw 2018 شرکت پرند", "Parand Corel Draw 2018 Learning Software", "Tutorial", 450000, "Parand", "https://4.bp.blogspot.com/-hzmEItkapcI/XDDbfNVmrTI/AAAAAAAAJIA/LW_zmNyB0zMupvAJidmkZGsAJ4td1DGlwCLcBGAs/s1600/FirstVersions_CorelDRAW-2018.png"));
        items_viewModel.insertInto_productsTable_local(new Products("نرم افزار آموزش زبان برنامه نویسی CSS/CSS3 نشر نوآوران", "CSS/CSS3", "Tutorial", 450000, "Noavaran", "https://s.mobile.ir/Static/cache/prd/38980-30_Youth_01_0_0.jpg"));
        items_viewModel.insertInto_productsTable_local(new Products("آموزش ماکروسافت آفیس 2019", "Microsoft Office 2019", "Tutorial", 450000, "Noavaran", "https://dkstatics-public.digikala.com/digikala-products/120663713.jpg?x-oss-process=image/resize,h_1600/quality,q_80"));
        items_viewModel.insertInto_productsTable_local(new Products("مجموعه نرم افزاری iKING 2020 شرکت پرند ", "KING Software package", "Tutorial", 450000, "Parand", "https://dkstatics-public.digikala.com/digikala-products/119693952.jpg?x-oss-process=image/resize,h_1600/quality,q_80"));


        items_viewModel.insertInto_productsTable_local(new Products("گوشی موبایل سامسونگ A51", "Samsung A51", "Mobile", 5850000, "Samsung", "https://s.mobile.ir/Static/cache/prd/38980-30_Youth_01_0_0.jpg"));
        items_viewModel.insertInto_productsTable_local(new Products("گوشی موبایل موتورولا Moto G 5G Plus", "Moto G 5G Plus", "Mobile", 8000000, "Motorola", "https://s.mobile.ir/Static/cache/prd/38984-Moto_G_5G_Plus_01_0_0.jpg"));
        items_viewModel.insertInto_productsTable_local(new Products("تصاویر گوشی موبایل اپل iPhone SE 2020", "iPhone SE 2020", "Mobile", 22000000, "Apple", "https://s.mobile.ir/Static/cache/prd/38904-iPhone_SE_2020_01_0_0.jpg"));
        items_viewModel.insertInto_productsTable_local(new Products("تصاویر گوشی موبایل هواوی Y7p", "Huawei Y7P", "Mobile", 4750000, "Huawei", "https://s.mobile.ir/Static/cache/prd/38833-Y7p_01_0_0.jpg"));
        items_viewModel.insertInto_productsTable_local(new Products("فروشندگان و قیمت گوشی موبایل سامسونگ Galaxy A71 5G", "Samsung A71", "Mobile", 8850000, "Samsung", "https://s.mobile.ir/Static/cache/prd/38893-Galaxy_A71_5G_01_0_0.jpg"));
        items_viewModel.insertInto_productsTable_local(new Products("تصاویر گوشی موبایل سامسونگ Galaxy Note10 Lite", "Galaxy Note10 Lite", "Mobile", 20000000, "Samsung", "https://s.mobile.ir/Static/cache/prd/38790-Galaxy_Note10_Lite_01_0_0.jpg"));
        items_viewModel.insertInto_productsTable_local(new Products("تصاویر گوشی موبایل گوگل Pixel 4", "Pixel 4", "Mobile", 11750000, "Google", "https://s.mobile.ir/Static/cache/prd/38716-Pixel_4_01_0_0.jpg"));
        items_viewModel.insertInto_productsTable_local(new Products("فروشندگان و قیمت گوشی موبایل سامسونگ Galaxy A71 5G", "Samsung A71", "Mobile", 8850000, "Samsung", "https://s.mobile.ir/Static/cache/prd/38893-Galaxy_A71_5G_01_0_0.jpg"));

    }

    private List<Products> getData() {
        //--- Get all data from local database by DAO -------------------------------------------

        list = new ArrayList<Products>();
        list.add(new Products(1, "نام محصول", "Samsung A51", "Mobile", 5850000, "Samsung", "https://s.mobile.ir/Static/cache/prd/38980-30_Youth_01_0_0.jpg"));
        list.add(new Products(2, "نام محصول", "Samsung A51", "Mobile", 5850000, "Samsung", "https://s.mobile.ir/Static/cache/prd/38980-30_Youth_01_0_0.jpg"));
        list.add(new Products(3, "نام محصول", "Samsung A51", "Mobile", 5850000, "Samsung", "https://s.mobile.ir/Static/cache/prd/38980-30_Youth_01_0_0.jpg"));
        list.add(new Products(4, "نام محصول", "Samsung A51", "Mobile", 5850000, "Samsung", "https://s.mobile.ir/Static/cache/prd/38980-30_Youth_01_0_0.jpg"));
        list.add(new Products(5, "نام محصول", "Samsung A51", "Mobile", 5850000, "Samsung", "https://s.mobile.ir/Static/cache/prd/38980-30_Youth_01_0_0.jpg"));
        list.add(new Products(6, "نام محصول", "Samsung A51", "Mobile", 5850000, "Samsung", "https://s.mobile.ir/Static/cache/prd/38980-30_Youth_01_0_0.jpg"));
        list.add(new Products(7, "نام محصول", "Samsung A51", "Mobile", 5850000, "Samsung", "https://s.mobile.ir/Static/cache/prd/38980-30_Youth_01_0_0.jpg"));
        list.add(new Products(8, "نام محصول", "Samsung A51", "Mobile", 5850000, "Samsung", "https://s.mobile.ir/Static/cache/prd/38980-30_Youth_01_0_0.jpg"));


        return list;
    }

    private LiveData<List<Products>> selectAll_Product() {

        LiveData<List<Products>> liveData_Product_select = items_viewModel.selectAll_Products();

        liveData_Product_select.observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {

                for (Products item : products) {
                    Log.d("TAG", "onChanged: " + item.getProduct_ID() + " / " + item.getProduct_Name_en() + " / " + item.getProduct_Category());
                }
            }
        });

        return liveData_Product_select;
    }

    private void getDataFromInternet() {
        productList = items_viewModel.getAllProducts();
        discountItemList = items_viewModel.getDiscountItems();
    }

    // ------------------------------------    گرفتن اطلاعات از وب سرور ---------------------------

    private void webService_GET_Discount() {
        String BASE_URL = "https://run.mocky.io";

        //1) Create Instance
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        //2) Create API
        WebServiceAPI api = retrofit.create(WebServiceAPI.class);

        //3) Create Requests
        Call<ArrayList<Discount_Items_entity>> list_DiscountItems_request = api.getDiscountItems();
        // request in Queue Methods
        list_DiscountItems_request.enqueue(new Callback<ArrayList<Discount_Items_entity>>() {

            // در صورت موفقیت وارد این متد می شود
            @Override
            public void onResponse(Call<ArrayList<Discount_Items_entity>> call, Response<ArrayList<Discount_Items_entity>> response) {
                ArrayList<Discount_Items_entity> list_body_discount = response.body();


                String str = "";
                for (Discount_Items_entity discount_items_entity : list_body_discount) {
                    str = str + "id: " + discount_items_entity.getItem_id() + " Item Name: " + discount_items_entity.getItem_name() + " \n";
                    Log.d("discount", str);

                    items_viewModel.insert_discountItems(
                            new Discount_Items_entity(discount_items_entity.getItem_id(),
                                    discount_items_entity.getItem_name(),
                                    discount_items_entity.getDiscount_percent(),
                                    discount_items_entity.getItem_price(),
                                    discount_items_entity.getItem_pic()));

                }

            }

            // در صورت بروز مشکل وارد این متد می شود
            @Override
            public void onFailure(Call<ArrayList<Discount_Items_entity>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void webService_GET_Products() {
        String BASE_URL = "https://run.mocky.io";

        //1) Create Instance
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        //2) Create API
        WebServiceAPI api = retrofit.create(WebServiceAPI.class);

        //3) Create Requests
        Call<ArrayList<Products>> list_products = api.getAllProducts();
        // request in Queue Methods
        list_products.enqueue(new Callback<ArrayList<Products>>() {

            // در صورت موفقیت وارد این متد می شود
            @Override
            public void onResponse(Call<ArrayList<Products>> call, Response<ArrayList<Products>> response) {
                ArrayList<Products> list_body_prduct = response.body();


                String str = "";
                for (Products products : list_body_prduct) {
                    str = str + "id: " + products.getProduct_ID() + " Item Name: " + products.getProduct_Name_en() + " \n";
                    Log.d("discount", str);

                    //---- Save Date into Local Database ---------------
                    items_viewModel.insertInto_productsTable_local(
                            new Products(products.getProduct_ID(),
                                    products.getProduct_Name_fa(),
                                    products.getProduct_Name_en(),
                                    products.getProduct_Category(),
                                    products.getProduct_Price(),
                                    products.getProduct_BrandName(),
                                    products.getProduct_img_url()));

                }

            }

            // در صورت بروز مشکل وارد این متد می شود
            @Override
            public void onFailure(Call<ArrayList<Products>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    // -----------------------------  گرفتن اطلاعات بصورت محلی برای تست -----------------------------
    private void getAllDiscountItems_Local() {

        items_viewModel.insert_discountItems(new Discount_Items_entity("لپ تاپ ایسوس","3%",17000000,"https://itmag.ua/upload/iblock/cd2/142.png"));
       /* items_viewModel.insert_discountItems(new Discount_Items_entity("کیف پول چرم", "35%", 15000, R.drawable.pic1));
        items_viewModel.insert_discountItems(new Discount_Items_entity("گوشی موبایل سامسونگ A20", "5%", 2300000, R.drawable.pic2));
        items_viewModel.insert_discountItems(new Discount_Items_entity("گوشی موبایل سامسونگ A51", "5%", 6200000, R.drawable.pic3));
        items_viewModel.insert_discountItems(new Discount_Items_entity("قاب گوشی سامسونگ A51", "20%", 13000, R.drawable.pic4));
        items_viewModel.insert_discountItems(new Discount_Items_entity("قاب گوشی سامسونگ A7", "15%", 19000, R.drawable.pic7));
        items_viewModel.insert_discountItems(new Discount_Items_entity(" گوشی سامسونگ A7", "17%", 3500000, R.drawable.pic6));*/


    }

    private LiveData<List<Discount_Items_entity>> selectAll_DiscountItems() {
        LiveData<List<Discount_Items_entity>> liveData_Product_select = items_viewModel.selectAll_discountItems();

        liveData_Product_select.observe(this, new Observer<List<Discount_Items_entity>>() {
            @Override
            public void onChanged(List<Discount_Items_entity> products) {

                for (Discount_Items_entity item : products) {
                    Log.d("getAll", "onChanged: " + item.getItem_name() + " / " + item.getDiscount_percent() + " / " + item.getItem_price());
                }
            }
        });
        return liveData_Product_select;
    }


}