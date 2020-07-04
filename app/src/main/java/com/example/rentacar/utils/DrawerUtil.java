package com.example.rentacar.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.example.rentacar.Aktivnosti.LoginActivity;
import com.example.rentacar.Aktivnosti.MasterViewActivity;
import com.example.rentacar.Aktivnosti.OmiljeniActivity;
import com.example.rentacar.Aktivnosti.RegisterActivity;
import com.example.rentacar.Aktivnosti.SettingsActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.example.rentacar.Aktivnosti.MasterViewActivity;
import com.example.rentacar.R;

public class DrawerUtil {

    public static void getDrawer(final Activity activity, Toolbar toolbar, Session sesija) {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem drawerEmptyItem= new PrimaryDrawerItem().withIdentifier(0).withName("");
        drawerEmptyItem.withEnabled(false);
        /*PrimaryDrawerItem drawerItemManagePlayers = new PrimaryDrawerItem().withIdentifier(1)
                .withName(R.string.manage_player).withIcon(R.drawable.ic_menu_camera);
        PrimaryDrawerItem drawerItemManagePlayersTournaments = new PrimaryDrawerItem()
                .withIdentifier(2).withName(R.string.tournament).withIcon(R.drawable.ic_menu_gallery);*/
        int textColor = 0;
        int backgroundColor = 0;
        int pocetnaSlika = 0;
        int prijavaSlika = 0;
        int registracijaSlika = 0;
        int podesavanjeSlika = 0;
        int odjavaSlika = 0;
        int omiljeniSlika = 0;

        switch (AppCompatDelegate.getDefaultNightMode()){
            case AppCompatDelegate.MODE_NIGHT_YES:
                backgroundColor= 0xFF1F1B24;
                textColor= Color.LTGRAY;
                pocetnaSlika= R.drawable.ic_home_black_24dp_siva;
                prijavaSlika=R.drawable.ic_account_circle_black_24dp_siva;
                registracijaSlika= R.drawable.ic_account_box_black_24dp_siva;
                podesavanjeSlika=R.drawable.ic_settings_black_24dp_siva;
                odjavaSlika= R.drawable.ic_subdirectory_arrow_right_black_24dp_siva;
                omiljeniSlika = R.drawable.favorite_foreground;
                break;
            default:
                backgroundColor= Color.WHITE;
                textColor = Color.BLACK;
                pocetnaSlika= R.drawable.ic_home_black_24dp;
                prijavaSlika=R.drawable.ic_account_circle_black_24dp;
                registracijaSlika= R.drawable.ic_account_box_black_24dp;
                podesavanjeSlika=R.drawable.ic_settings_black_24dp;
                odjavaSlika= R.drawable.ic_subdirectory_arrow_right_black_24dp;
                omiljeniSlika= R.drawable.favorite_background;
                break;

        }

        PrimaryDrawerItem drawerItemPocetna = new PrimaryDrawerItem().withIdentifier(1)
                .withName(R.string.pocetna).withIcon(pocetnaSlika).withTextColor(textColor);
        PrimaryDrawerItem drawerItemPrijava = new PrimaryDrawerItem().withIdentifier(2)
                .withName(R.string.prijava).withIcon(prijavaSlika).withTextColor(textColor);
        PrimaryDrawerItem drawerItemRegistracija = new PrimaryDrawerItem().withIdentifier(3)
                .withName(R.string.registracija).withIcon(registracijaSlika).withTextColor(textColor);
        PrimaryDrawerItem drawerItemPodesavanja = new PrimaryDrawerItem().withIdentifier(4)
                .withName(R.string.podesavanja).withIcon(podesavanjeSlika).withTextColor(textColor);
        PrimaryDrawerItem drawerItemOdjava = new PrimaryDrawerItem().withIdentifier(5)
                .withName(R.string.odjava).withIcon(odjavaSlika).withTextColor(textColor);
        PrimaryDrawerItem drawerItemOmiljeni = new PrimaryDrawerItem().withIdentifier(6)
                .withName(R.string.omiljeni).withIcon(omiljeniSlika).withTextColor(textColor);

        String id = sesija.getKorisnikId();
        if (id == null || id.equals("")) {
            Drawer result = new DrawerBuilder()
                    .withActivity(activity)
                    .withToolbar(toolbar)
                    .withActionBarDrawerToggle(true)
                    .withActionBarDrawerToggleAnimated(true)
                    .withCloseOnClick(true)
                    .withSelectedItem(-1)
                    .addDrawerItems(
                            drawerEmptyItem,drawerEmptyItem,drawerEmptyItem,
                        /*drawerItemManagePlayers,
                        drawerItemManagePlayersTournaments,*/
                            new DividerDrawerItem(),
                            drawerItemPocetna,
                            drawerItemPrijava,
                            drawerItemRegistracija,
                            drawerItemPodesavanja
                    )
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            if (drawerItem.getIdentifier() == 1) {
                                /*&& !(activity instanceof MasterViewActivity)*/
                                Intent intent = new Intent(activity, MasterViewActivity.class);
                                view.getContext().startActivity(intent);
                            }

                            if (drawerItem.getIdentifier() == 2 && !(activity instanceof LoginActivity)) {
                                Intent intent = new Intent(activity, LoginActivity.class);
                                view.getContext().startActivity(intent);
                            }

                            if (drawerItem.getIdentifier() == 3 && !(activity instanceof RegisterActivity)) {
                                Intent intent = new Intent(activity, RegisterActivity.class);
                                view.getContext().startActivity(intent);
                            }

                            if (drawerItem.getIdentifier() == 4 && !(activity instanceof SettingsActivity)) {
                                Intent intent = new Intent(activity, SettingsActivity.class);
                                view.getContext().startActivity(intent);
                            }
                            return true;
                        }
                    })
                    .withSliderBackgroundColor(backgroundColor)

                    .build();
        }
        else {
            Drawer result = new DrawerBuilder()
                    .withActivity(activity)
                    .withToolbar(toolbar)
                    .withActionBarDrawerToggle(true)
                    .withActionBarDrawerToggleAnimated(true)
                    .withCloseOnClick(true)
                    .withSelectedItem(-1)
                    .addDrawerItems(
                            drawerEmptyItem,drawerEmptyItem,drawerEmptyItem,
                        /*drawerItemManagePlayers,
                        drawerItemManagePlayersTournaments,*/
                            new DividerDrawerItem(),
                            drawerItemPocetna,
                            drawerItemOdjava,
                            drawerItemPodesavanja,
                            drawerItemOmiljeni
                    )
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            if (drawerItem.getIdentifier() == 1 && !(activity instanceof MasterViewActivity)) {
                                Intent intent = new Intent(activity, MasterViewActivity.class);
                                view.getContext().startActivity(intent);
                            }

                            if (drawerItem.getIdentifier() == 4 && !(activity instanceof SettingsActivity)) {
                                Intent intent = new Intent(activity, SettingsActivity.class);
                                view.getContext().startActivity(intent);
                            }

                            if (drawerItem.getIdentifier() == 5) {
                                sesija.destroy();
                                Intent intent = new Intent(activity, MasterViewActivity.class);
                                view.getContext().startActivity(intent);
                            }

                            if (drawerItem.getIdentifier() == 6) {
                                Intent intent = new Intent(activity, OmiljeniActivity.class);
                                view.getContext().startActivity(intent);
                            }

                            return true;
                        }
                    })
                    .withSliderBackgroundColor(backgroundColor)

                    .build();

        }
    }
}
