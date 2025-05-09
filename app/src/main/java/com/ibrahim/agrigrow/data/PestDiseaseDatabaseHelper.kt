package com.ibrahim.agrigrow.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PestDiseaseDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "pests.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE pests (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                description TEXT
            )
        """
        db.execSQL(createTable)

        val insertData = listOf(
            "INSERT INTO pests (name, description) VALUES ('Fall Armyworm', 'Damages maize leaves and ears by feeding heavily.')",
            "INSERT INTO pests (name, description) VALUES ('Tomato Leaf Curl Virus', 'Causes leaf curling and stunted tomato plants.')",
            "INSERT INTO pests (name, description) VALUES ('Bean Rust', 'Creates reddish-brown spots on bean leaves.')",
            "INSERT INTO pests (name, description) VALUES ('Maize Stem Borer', 'Larvae bore into maize stems, reducing yield.')",
            "INSERT INTO pests (name, description) VALUES ('Whiteflies', 'Suck sap from plants and transmit viruses.')",
            "INSERT INTO pests (name, description) VALUES ('Aphids', 'Damage crops by sucking sap and transmitting diseases.')",
            "INSERT INTO pests (name, description) VALUES ('Cutworms', 'Cut young seedlings at the soil line.')",
            "INSERT INTO pests (name, description) VALUES ('Potato Late Blight', 'Causes brown lesions and rot in leaves and tubers.')",
            "INSERT INTO pests (name, description) VALUES ('Root-knot Nematodes', 'Cause root galls and poor plant growth.')",
            "INSERT INTO pests (name, description) VALUES ('Red Spider Mites', 'Feed on leaf cells, causing discoloration.')",
            "INSERT INTO pests (name, description) VALUES ('Fruit Fly', 'Damages mangoes and other fruits by laying eggs.')",
            "INSERT INTO pests (name, description) VALUES ('Maize Lethal Necrosis', 'Combines two viruses causing severe maize loss.')",
            "INSERT INTO pests (name, description) VALUES ('Anthracnose', 'Fungal disease causing dark lesions on fruits and stems.')",
            "INSERT INTO pests (name, description) VALUES ('Cassava Mosaic Virus', 'Leads to leaf deformation and reduced yield.')",
            "INSERT INTO pests (name, description) VALUES ('Coffee Berry Disease', 'Dark spots on coffee berries causing premature drop.')",
            "INSERT INTO pests (name, description) VALUES ('Banana Weevil', 'Bores into banana corms, weakening the plant.')",
            "INSERT INTO pests (name, description) VALUES ('Thrips', 'Cause silvering of leaves and fruit deformities.')",
            "INSERT INTO pests (name, description) VALUES ('Powdery Mildew', 'White powdery growth on leaves and stems.')",
            "INSERT INTO pests (name, description) VALUES ('Bacterial Wilt', 'Causes sudden wilting and death of crops.')",
            "INSERT INTO pests (name, description) VALUES ('Cabbage Looper', 'Feeds on cabbage leaves, creating holes.')",
            "INSERT INTO pests (name, description) VALUES ('Tomato Blight', 'Leads to leaf and fruit rot in tomatoes.')",
            "INSERT INTO pests (name, description) VALUES ('Citrus Canker', 'Bacterial infection causing scabs on fruits and leaves.')",
            "INSERT INTO pests (name, description) VALUES ('Stem Rust (Wheat)', 'Red pustules on wheat stems reducing yield.')",
            "INSERT INTO pests (name, description) VALUES ('Downy Mildew', 'Gray mold under leaves, common in cucurbits.')",
            "INSERT INTO pests (name, description) VALUES ('Fusarium Wilt', 'Yellowing and wilting caused by soil-borne fungi.')",
            "INSERT INTO pests (name, description) VALUES ('Leaf Spot (Beans)', 'Dark spots with yellow halos on bean leaves.')",
            "INSERT INTO pests (name, description) VALUES ('Sorghum Midge', 'Damages flowering sorghum by feeding on grains.')",
            "INSERT INTO pests (name, description) VALUES ('Grain Weevils', 'Infest stored maize, sorghum, and wheat.')",
            "INSERT INTO pests (name, description) VALUES ('Stalk Rot', 'Causes stem collapse in maize and sorghum.')",
            "INSERT INTO pests (name, description) VALUES ('Rice Blast', 'Destroys rice plants at all growth stages.')",
            "INSERT INTO pests (name, description) VALUES ('Tuta absoluta', 'Major tomato pest that mines leaves and fruits.')",
            "INSERT INTO pests (name, description) VALUES ('Peach Fruit Moth', 'Damages young fruit and reduces quality.')",
            "INSERT INTO pests (name, description) VALUES ('Soybean Rust', 'Creates orange-brown pustules on soybean leaves.')",
            "INSERT INTO pests (name, description) VALUES ('Sunflower Charcoal Rot', 'Root and stem rot leading to plant death.')",
            "INSERT INTO pests (name, description) VALUES ('Chickpea Pod Borer', 'Damages pods and seeds of chickpeas.')",
            "INSERT INTO pests (name, description) VALUES ('Barley Stripe Rust', 'Yellow stripes on leaves and reduced yield.')",
            "INSERT INTO pests (name, description) VALUES ('Okra Leafhopper', 'Feeds on okra sap and transmits viruses.')",
            "INSERT INTO pests (name, description) VALUES ('Onion Thrips', 'Feed on onion leaves, causing silvery patches.')",
            "INSERT INTO pests (name, description) VALUES ('Spinach Downy Mildew', 'Yellow spots and gray mold on spinach.')"

        )

        insertData.forEach { db.execSQL(it) }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS pests")
        onCreate(db)
    }
}

