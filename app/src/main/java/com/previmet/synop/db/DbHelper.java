package com.previmet.synop.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vince on 20.12.2014.
 */
public class DbHelper extends SQLiteOpenHelper {

    private String[] countries = {"Suisse", "France", "Belgique"};
    private String[] stations = {"Suisse", "France", "Belgique"};


    public DbHelper(Context context) {

        super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DbContract.Country.SQL_CREATE_TABLE);
        db.execSQL(DbContract.Station.SQL_CREATE_TABLE);
        db.execSQL(DbContract.Favorite.SQL_CREATE_TABLE);

        // populate some initial values
        Db.initialize(db);

        // countries
        long idSuisse = Db.addCountry("Suisse");
        long idFrance = Db.addCountry("France");
        long idBelgique = Db.addCountry("Belgique");

        Db.addStation("Koksijde", "6400", 4, 51.0848,2.6468, idBelgique);
        Db.addStation("Oostende", "6407",5,51.2002,2.8701,idBelgique);
        Db.addStation("Roeselare/Beitem", "6414",25,50.8999,3.1195,idBelgique);
        Db.addStation("Charleroi", "6449",187,50.4666,4.4508,idBelgique);
        Db.addStation("Bruxelles-National", "6451",55,50.901,4.5339,idBelgique);
        Db.addStation("Florennes", "6456",279,50.232,4.648,idBelgique);
        Db.addStation("St-Hubert", "6476",557,50.0282,5.4175,idBelgique);
        Db.addStation("Kleine Brogel", "6479",55,51.1681,5.4658,idBelgique);
        Db.addStation("Buzenol", "6484",324,49.6202,5.5797,idBelgique);
        Db.addStation("Elsenborn", "6496",564,50.4666,6.1844,idBelgique);
        Db.addStation("Basel / Binningen", "6601",316,47.5459,7.5498,idSuisse);
        Db.addStation("Delémont", "6602",439,47.351702,7.349554,idSuisse);
        Db.addStation("Neuchâtel", "6604",485,46.9999,6.9531,idSuisse);
        Db.addStation("Chasseral", "6605",1594,47.1317,7.0543,idSuisse);
        Db.addStation("Cressier", "6606",431,47.0476,7.0591,idSuisse);
        Db.addStation("Chaumont", "6608",1136,47.04916,6.978821,idSuisse);
        Db.addStation("Le Moléson", "6609",1974,46.5461,7.0178,idSuisse);
        Db.addStation("Payerne", "6610",490,46.8115,6.9424,idSuisse);
        Db.addStation("La Chaux-de-Fonds", "6612",1017,47.0832,6.7923,idSuisse);
        Db.addStation("Fahy", "6616",596,47.4238,6.9411,idSuisse);
        Db.addStation("La Brévine", "6617",1050,46.983836,6.61029,idSuisse);
        Db.addStation("Mathod", "6618",437,46.737018,6.56803,idSuisse);
        Db.addStation("Bullet / La Frétaz", "6619",1205,46.8406,6.5763,idSuisse);
        Db.addStation("Schaffhausen", "6620",438,47.6897,8.62,idSuisse);
        Db.addStation("Güttingen", "6621",440,47.6017,9.2793,idSuisse);
        Db.addStation("Salen Reutenen", "6623",718,47.651199,9.023931,idSuisse);
        Db.addStation("Hallau", "6624",419,47.6972,8.4704,idSuisse);
        Db.addStation("Fribourg / Posieux", "6625",646,46.7713,7.1137,idSuisse);
        Db.addStation("Gösgen", "6626",380,47.363,7.9736,idSuisse);
        Db.addStation("Château-d'Oex", "6627",1029,46.4797,7.1396,idSuisse);
        Db.addStation("Plaffeien", "6628",1042,46.7476,7.2659,idSuisse);
        Db.addStation("Bern / Zollikofen", "6631",553,46.9466,7.4922,idSuisse);
        Db.addStation("Grenchen", "6632",430,47.179,7.4151,idSuisse);
        Db.addStation("Buchs / Aarau", "6633",387,47.3843,8.0794,idSuisse);
        Db.addStation("Koppigen", "6635",484,47.118843,7.605486,idSuisse);
        Db.addStation("Mühleberg", "6636",480,46.9732,7.2781,idSuisse);
        Db.addStation("Meiringen", "6637",588,46.7321,8.1692,idSuisse);
        Db.addStation("Langnau", "6638",745,46.9396,7.8064,idSuisse);
        Db.addStation("Napf", "6639",1404,47.0046,7.94,idSuisse);
        Db.addStation("Möhlin", "6641",344,47.5721,7.8778,idSuisse);
        Db.addStation("Wynau", "6643",422,47.255,7.7874,idSuisse);
        Db.addStation("Mosen", "6644",452,47.243801,8.232782,idSuisse);
        Db.addStation("Rünenberg", "6645",611,47.4345,7.8793,idSuisse);
        Db.addStation("Beznau", "6646",326,47.5572,8.2332,idSuisse);
        Db.addStation("Egolzwil", "6648",521,47.1793,8.0047,idSuisse);
        Db.addStation("Luzern", "6650",454,47.0364,8.3009,idSuisse);
        Db.addStation("Schüpfheim", "6651",742,46.947,8.0123,idSuisse);
        Db.addStation("Engelberg", "6655",1036,46.8216,8.4104,idSuisse);
        Db.addStation("Brienz", "6656",567,46.740757,8.060776,idSuisse);
        Db.addStation("Giswil", "6657",475,46.8494,8.1901,idSuisse);
        Db.addStation("Pilatus", "6659",2106,46.9788,8.2523,idSuisse);
        Db.addStation("Zürich / Fluntern", "6660",556,47.3778,8.5657,idSuisse);
        Db.addStation("Zürich / Affoltern", "6664",444,47.427676,8.517885,idSuisse);
        Db.addStation("Leibstadt", "6666",341,47.5972,8.1882,idSuisse);
        Db.addStation("Laegern", "6669",868,47.48,8.4,idSuisse);
        Db.addStation("Zürich-Kloten", "6670",436,47.4796,8.536,idSuisse);
        Db.addStation("Steckborn", "6671",398,47.6686,8.9814,idSuisse);
        Db.addStation("Altdorf", "6672",438,46.887,8.6218,idSuisse);
        Db.addStation("Wädenswil", "6673",485,47.2206,8.6777,idSuisse);
        Db.addStation("Cham", "6674",442,47.18829,8.46462,idSuisse);
        Db.addStation("Einsiedeln", "6675",910,47.132956,8.756531,idSuisse);
        Db.addStation("Oberägeri", "6676",724,47.13358,8.60808,idSuisse);
        Db.addStation("Aadorf / Tänikon", "6679",539,47.4798,8.9048,idSuisse);
        Db.addStation("Säntis", "6680",2502,47.2494,9.3436,idSuisse);
        Db.addStation("St. Gallen", "6681",776,47.4254,9.3984,idSuisse);
        Db.addStation("Elm", "6682",958,46.9237,9.1753,idSuisse);
        Db.addStation("Schmerikon", "6683",408,47.2246,8.9402,idSuisse);
        Db.addStation("Glarus", "6685",517,47.0345,9.0669,idSuisse);
        Db.addStation("Bad Ragaz", "6686",496,47.0165,9.5025,idSuisse);
        Db.addStation("Quinten", "6687",419,47.1287,9.216,idSuisse);
        Db.addStation("Crap Masegn", "6688",2480,46.8422,9.1799,idSuisse);
        Db.addStation("Hörnli", "6689",1132,47.37085,8.941612,idSuisse);
        Db.addStation("Altenrhein", "6690",398,47.4836,9.5667,idSuisse);
        Db.addStation("Ebnat-Kappel", "6693",623,47.2733,9.1085,idSuisse);
        Db.addStation("Andermatt", "6695",1438,46.630825,8.58051,idSuisse);
        Db.addStation("Genève-Cointrin", "6700",413,46.2474,6.1277,idSuisse);
        Db.addStation("La Dôle", "6702",1670,46.4246,6.0994,idSuisse);
        Db.addStation("Bière", "6704",684,46.5248,6.3424,idSuisse);
        Db.addStation("Nyon / Changins", "6705",409,46.401,6.2277,idSuisse);
        Db.addStation("St-Prex", "6706",425,46.4836,6.443,idSuisse);
        Db.addStation("Villars - Tiercelin", "6707",859,46.62176,6.71007,idSuisse);
        Db.addStation("Oron", "6708",827,46.5722,6.8581,idSuisse);
        long idBouveret = Db.addStation("Bouveret", "6709",374,46.393444,6.857012,idSuisse);
        Db.addStation("Pully", "6711",456,46.5123,6.6674,idSuisse);
        long idAigle = Db.addStation("Aigle", "6712",408,46.3266,6.9244,idSuisse);
        Db.addStation("Les Diablerets", "6714",2964,46.3267,7.2037,idSuisse);
        Db.addStation("Evionnaz", "6715",482,46.18295,7.02675,idSuisse);
        Db.addStation("Col du Grand St-Bernard", "6717",2472,45.8687,7.1707,idSuisse);
        Db.addStation("Sion", "6720",482,46.2291,7.3619,idSuisse);
        Db.addStation("Binn", "6721",1479,46.36766,8.1923,idSuisse);
        Db.addStation("Evolene-Villaz", "6722",1825,46.1121,7.5086,idSuisse);
        Db.addStation("Les Attelas", "6723",2727,46.099122,7.268748,idSuisse);
        Db.addStation("Montana", "6724",1427,46.2987,7.4607,idSuisse);
        Db.addStation("Blatten, Lötschental", "6725",1538,46.420398,7.823196,idSuisse);
        Db.addStation("Visp", "6727",639,46.2967,7.8846,idSuisse);
        Db.addStation("Grächen", "6728",1605,46.195314,7.83679,idSuisse);
        Db.addStation("Jungfraujoch", "6730",3580,46.5474,7.9853,idSuisse);
        Db.addStation("Thun", "6731",570,46.74976,7.58525,idSuisse);
        Db.addStation("Boltigen", "6733",820,46.623536,7.384165,idSuisse);
        Db.addStation("Interlaken", "6734",577,46.6722,7.8701,idSuisse);
        Db.addStation("Adelboden", "6735",1320,46.4947,7.5704,idSuisse);
        Db.addStation("Eggishorn", "6739",2893,46.4265,8.0927,idSuisse);
        Db.addStation("Titlis", "6740",3040,46.770507,8.425805,idSuisse);
        Db.addStation("Grimsel Hospiz", "6744",1980,46.5716,8.3332,idSuisse);
        Db.addStation("Ulrichen", "6745",1346,46.5048,8.3081,idSuisse);
        Db.addStation("Monte Rosa-Plattje", "6747",2885,45.9566,7.8145,idSuisse);
        Db.addStation("Zermatt", "6748",1638,46.0218,7.7484,idSuisse);
        Db.addStation("Gornergrat", "6749",3129,46.0114,7.7668499,idSuisse);
        Db.addStation("Gütsch ob Andermatt", "6750",2287,46.653457,8.616244,idSuisse);
        Db.addStation("Robièi", "6751",1895,46.443,8.5133,idSuisse);
        Db.addStation("Cevio", "6752",417,46.32049,8.603162,idSuisse);
        Db.addStation("Piotta", "6753",990,46.5147,8.6881,idSuisse);
        Db.addStation("Matro", "6754",2171,46.4101,8.92473,idSuisse);
        Db.addStation("Acquarossa / Comprovasco", "6756",575,46.4594,8.9356,idSuisse);
        Db.addStation("Grono", "6758",324,46.254991,9.163721,idSuisse);
        Db.addStation("Cimetta", "6759",1661,46.20042,8.791642,idSuisse);
        Db.addStation("Locarno / Monti", "6760",383,46.1722,8.7874,idSuisse);
        Db.addStation("Magadino / Cadenazzo", "6762",203,46.16,8.9336,idSuisse);
        Db.addStation("Lugano", "6770",300,46.0038,8.9601,idSuisse);
        Db.addStation("Stabio", "6771",353,45.8433,8.9321,idSuisse);
        Db.addStation("Monte Generoso", "6777",1600,45.927605,9.017872,idSuisse);
        Db.addStation("Buffalora", "6778",1968,46.6481,10.2671,idSuisse);
        Db.addStation("Segl - Maria", "6779",1804,46.43232,9.76231,idSuisse);
        Db.addStation("Weissfluhjoch", "6780",2690,46.8333,9.8063,idSuisse);
        Db.addStation("Disentis", "6782",1197,46.7065,8.8534,idSuisse);
        Db.addStation("S. Bernardino", "6783",1639,46.4635,9.1846,idSuisse);
        Db.addStation("Davos", "6784",1594,46.8129,9.8434,idSuisse);
        Db.addStation("Chur", "6786",556,46.8704,9.5305,idSuisse);
        Db.addStation("Andeer", "6787",987,46.6101,9.4319,idSuisse);
        Db.addStation("Vicosoprano", "6788",1089,46.35297,9.627688,idSuisse);
        Db.addStation("Ilanz", "6789",698,46.77504,9.21533,idSuisse);
        Db.addStation("Schiers", "6790",626,46.9755,9.66805,idSuisse);
        Db.addStation("Piz Corvatsch", "6791",3305,46.418,9.8211,idSuisse);
        Db.addStation("Samedan", "6792",1709,46.5264,9.8781,idSuisse);
        Db.addStation("Valbella", "6793",1569,46.7549,9.5544,idSuisse);
        Db.addStation("Poschiavo / Robbia", "6794",1078,46.3466,10.0611,idSuisse);
        Db.addStation("Piz Martegnas", "6795",2670,46.5773,9.5296,idSuisse);
        Db.addStation("Sta. Maria, Val Müstair", "6796",1383,46.602168,10.426265,idSuisse);
        Db.addStation("Scuol", "6798",1304,46.7932,10.2832,idSuisse);
        Db.addStation("Naluns / Schlivera", "6799",2400,46.8171,10.2613,idSuisse);
        Db.addStation("Boulogne", "7002",73,50.7339,1.5978,idFrance);
        Db.addStation("Le Touquet-Paris-Plage", "7003",10,50.5163,1.6241,idFrance);
        Db.addStation("Abbeville", "7005",70,50.1331,1.8378,idFrance);
        Db.addStation("Dunkerque", "7010",11,51.0539,2.3412,idFrance);
        Db.addStation("Lille-Lesquin", "7015",48,50.5669,3.099,idFrance);
        Db.addStation("Cap de La Hague", "7020",3,49.7263,-1.9397,idFrance);
        Db.addStation("Cherbourg - Maupertus", "7024",135,49.6484,-1.4739,idFrance);
        Db.addStation("Caen-Carpiquet ", "7027",78,49.1809,-0.4606,idFrance);
        Db.addStation("Le Havre - Cap de la Hève", "7028",100,49.5093,0.0684,idFrance);
        Db.addStation("Deauville", "7031",146,49.3667,0.0832,idFrance);
        Db.addStation("Rouen-Boos", "7037",151,49.3864,1.1835,idFrance);
        Db.addStation("Evreux - Fauville", "7038",138,49.0261,1.2218,idFrance);
        Db.addStation("Dieppe", "7040",33,49.9343,1.0948,idFrance);
        Db.addStation("Beauvais-Tille", "7055",89,49.4468,2.1285,idFrance);
        Db.addStation("Creil", "7057",88,49.2603,2.5173,idFrance);
        Db.addStation("Saint-Quentin - Roupy", "7061",98,49.8188,3.2066,idFrance);
        Db.addStation("Reims-Prunay", "7072",83,49.2,4.1581,idFrance);
        Db.addStation("Charleville-Mezieres", "7075",149,49.7828,4.6345,idFrance);
        Db.addStation("Metz / Frescaty", "7090",190,49.0725,6.1231,idFrance);
        Db.addStation("Ouessant-Stiff", "7100",35,48.4762,-5.0532,idFrance);
        Db.addStation("Brest", "7110",99,48.3982,-4.4911,idFrance);
        Db.addStation("Ploumana'ch - Perros", "7117",50,48.827,-3.4727,idFrance);
        Db.addStation("Lannion - Servel", "7118",87,48.757,-3.468,idFrance);
        Db.addStation("Saint-Brieuc - Armor", "7120",136,48.537,-2.8513,idFrance);
        Db.addStation("Dinard - St Malo", "7125",58,48.5902,-2.0732,idFrance);
        Db.addStation("Rennes-St Jacques", "7130",37,48.0711,-1.7369,idFrance);
        Db.addStation("Laval-Entrammes", "7134",96,48.0335,-0.7362,idFrance);
        Db.addStation("Alençon - Valframbert", "7139",144,48.4443,0.1067,idFrance);
        Db.addStation("Chartres - Champhol", "7143",155,48.4617,1.512,idFrance);
        Db.addStation("Trappes", "7145",168,48.7706,2.0083,idFrance);
        Db.addStation("Toussus Le Noble", "7146",154,48.7506,2.1073,idFrance);
        Db.addStation("Villacoublay - Vélizy", "7147",174,48.7708,2.2045,idFrance);
        Db.addStation("Paris-Orly", "7149",89,48.7219,2.3524,idFrance);
        Db.addStation("Paris / Le Bourget", "7150",93,48.967,2.425,idFrance);
        Db.addStation("Melun - Villaroche", "7153",91,48.6103,2.8,idFrance);
        Db.addStation("Paris-Montsouris", "7156",75,48.8217,2.3374,idFrance);
        Db.addStation("Paris - Roissy/Charles-de-Gaulle", "7157",108,49.0191,2.5338,idFrance);
        Db.addStation("Troyes-Barberey", "7168",112,48.328,4.0229,idFrance);
        Db.addStation("Saint-Dizier - Robinson", "7169",139,48.6355,4.9028,idFrance);
        Db.addStation("Nancy-Essey", "7180",212,48.6872,6.2218,idFrance);
        Db.addStation("Nancy-Ochey", "7181",336,48.579,5.9571,idFrance);
        Db.addStation("Strasbourg", "7190",153,48.5391,7.6222,idFrance);
        Db.addStation("Quimper-Pluguffan", "7201",92,47.9732,-4.1724,idFrance);
        Db.addStation("Ile de Groix - Beg Melen", "7203",45,47.6529,-3.5023,idFrance);
        Db.addStation("Lorient-Lann Bihoué", "7205",42,47.768,-3.4418,idFrance);
        Db.addStation("Nantes-Atlantique", "7222",27,47.1561,-1.6055,idFrance);
        Db.addStation("Angers - Marcé", "7230",59,47.5793,-0.3274,idFrance);
        Db.addStation("Tours - St Symphorien", "7240",112,47.4449,0.7287,idFrance);
        Db.addStation("Blois", "7245",121,47.6784,1.2097,idFrance);
        Db.addStation("Romorantin - Pruniers", "7247",84,47.3166,1.6825,idFrance);
        Db.addStation("Orléans - Bricy", "7249",126,47.9912,1.7497,idFrance);
        Db.addStation("Bourges", "7255",161,47.0582,2.3617,idFrance);
        Db.addStation("Nevers-Marzy", "7260",180,46.9994,3.1133,idFrance);
        Db.addStation("Auxerre", "7265",207,47.8049,3.5448,idFrance);
        Db.addStation("Dijon", "7280",222,47.3166,5.0166,idFrance);
        Db.addStation("Langres", "7283",467,47.8444,5.3365,idFrance);
        Db.addStation("Besançon", "7288",307,47.25,6.0333,idFrance);
        Db.addStation("Luxeuil", "7292",278,47.7818,6.3867,idFrance);
        Db.addStation("Bâle-Mulhouse", "7299",263,47.6151,7.5092,idFrance);
        Db.addStation("Ile d'Yeu - St Sauveur", "7300",32,46.6939,-2.3303,idFrance);
        Db.addStation("La Roche sur Yon - Les Ajoncs", "7306",90,46.6998,-1.3821,idFrance);
        Db.addStation("Pointe de Chassiron", "7314",11,46.0477,-1.4105,idFrance);
        Db.addStation("La Rochelle-Le Bout Blanc", "7315",4,46.1523,-1.1594,idFrance);
        Db.addStation("Niort - Souche", "7330",59,46.3166,-0.4004,idFrance);
        Db.addStation("Poitiers-Biard", "7335",129,46.5812,0.3004,idFrance);
        Db.addStation("Chateauroux", "7354",160,46.8593,1.7261,idFrance);
        Db.addStation("Vichy-Charmeil", "7374",249,46.169,3.4013,idFrance);
        Db.addStation("Mâcon - Charnay", "7385",216,46.2969,4.799,idFrance);
        Db.addStation("Cognac-Châteaubernard", "7412",30,45.668,-0.313,idFrance);
        Db.addStation("Limoges-Bellegarde", "7434",396,45.8649,1.1839,idFrance);
        Db.addStation("Brive - La Roche", "7438",111,45.1484,1.4745,idFrance);
        Db.addStation("Clermont-Ferrand - Aulnat", "7460",332,45.7888,3.1631,idFrance);
        Db.addStation("Le Puy-Loudes", "7471",833,45.0754,3.7641,idFrance);
        Db.addStation("Lyon / Bron", "7480",200,45.75,4.85,idFrance);
        Db.addStation("Lyon / St-Exupéry", "7481",248,45.7215,5.0861,idFrance);
        Db.addStation("Amberieu", "7482",250,45.9612,5.3193,idFrance);
        Db.addStation("Grenoble / St. Geoirs", "7486",384,45.3614,5.3374,idFrance);
        Db.addStation("Chambery / Aix-Les-Bains", "7491",235,45.7,5.9166,idFrance);
        Db.addStation("Bourg-St-Maurice", "7497",865,45.613,6.7627,idFrance);
        Db.addStation("Lège - Cap Ferret", "7500",9,44.6319,-1.2478,idFrance);
        Db.addStation("Biscarrosse", "7503",33,44.4339,-1.2463,idFrance);
        Db.addStation("Bordeaux / Merignac", "7510",49,44.8332,-0.7193,idFrance);
        Db.addStation("Agen - La Garenne", "7524",59,44.177,0.5952,idFrance);
        Db.addStation("Bergerac-Roumaniere", "7530",51,44.8221,0.517,idFrance);
        Db.addStation("Gourdon", "7535",259,44.7469,1.3971,idFrance);
        Db.addStation("Aurillac", "7549",640,44.8955,2.422,idFrance);
        Db.addStation("Millau", "7558",715,44.1205,3.0199,idFrance);
        Db.addStation("Mont Aigoual", "7560",1567,44.1212,3.5814,idFrance);
        Db.addStation("Montélimar - Ancone", "7577",73,44.581,4.7377,idFrance);
        Db.addStation("Orange-Caritat", "7579",53,44.1405,4.8514,idFrance);
        Db.addStation("Carpentras", "7586",99,44.0832,5.0594,idFrance);
        Db.addStation("Château Arnoux - Saint-Auban", "7588",461,44.0634,5.9915,idFrance);
        Db.addStation("Saint-Jean-de-Luz - Pointe de Socoa", "7600",24,43.3949,-1.685,idFrance);
        Db.addStation("Biarritz-Anglet", "7602",69,43.472,-1.5319,idFrance);
        Db.addStation("Dax-Seyresse", "7603",31,43.6921,-1.0658,idFrance);
        Db.addStation("Mont-de-Marsan", "7607",59,43.9123,-0.4979,idFrance);
        Db.addStation("Pau-Uzein", "7610",188,43.3859,-0.4263,idFrance);
        Db.addStation("Tarbes - Ossun - Lourdes", "7621",360,43.1869,0.0051,idFrance);
        Db.addStation("Auch-Lamothe", "7622",121,43.6899,0.6031,idFrance);
        Db.addStation("Saint-Girons - Antichan", "7627",411,43.0021,1.1071,idFrance);
        Db.addStation("Toulouse / Blagnac", "7630",152,43.6397,1.3557,idFrance);
        Db.addStation("Albi-Le Séquestre", "7632",172,43.9152,2.1169,idFrance);
        Db.addStation("Carcassonne-Salvaza", "7635",126,43.2145,2.3099,idFrance);
        Db.addStation("Sète", "7641",80,43.3973,3.6926,idFrance);
        Db.addStation("Montpellier - Fréjorgues", "7643",5,43.5809,3.962,idFrance);
        Db.addStation("Marseille / Marignane", "7650",6,43.4381,5.2161,idFrance);
        Db.addStation("La Ciotat - Bec de l'Aigle", "7656",316,43.1749,5.5747,idFrance);
        Db.addStation("Toulon - La Mitre", "7660",24,43.1042,5.9328,idFrance);
        Db.addStation("Saint-Mandrier-sur-Mer - Cap Cépet", "7661",126,43.0781,5.9422,idFrance);
        Db.addStation("Leucate", "7666",42,42.9178,3.0599,idFrance);
        Db.addStation("Hyères - Le Palyvestre", "7667",2,43.0952,6.1473,idFrance);
        Db.addStation("Ile de Porquerolles", "7670",143,43.0,6.2276,idFrance);
        Db.addStation("Le Luc - Le Cannet-des-Maures", "7675",80,43.3843,6.3852,idFrance);
        Db.addStation("Ile du Levant", "7678",130,43.023,6.46,idFrance);
        Db.addStation("Cannes", "7684",3,43.5413,6.9511,idFrance);
        Db.addStation("Nice", "7690",4,43.7048,7.268,idFrance);
        Db.addStation("Perpignan - Rivesaltes", "7747",43,42.7437,2.8703,idFrance);
        Db.addStation("Cap Béar - Port-Vendres", "7749",82,42.5162,3.1335,idFrance);
        Db.addStation("Ajaccio - La Parata", "7752",140,41.9084,8.6186,idFrance);
        Db.addStation("Ile Rousse", "7753",142,42.6331,8.9225,idFrance);
        Db.addStation("Calvi - Ste Catherine", "7754",57,42.5245,8.7919,idFrance);
        Db.addStation("Ajaccio - Campo dell'Oro", "7761",6,41.9222,8.7955,idFrance);
        Db.addStation("Solenzara", "7765",17,41.9234,9.3957,idFrance);
        Db.addStation("Bonifacio - Cap Pertusato", "7770",105,41.3908,9.1661,idFrance);
        Db.addStation("Figari", "7780",22,41.508,9.0999,idFrance);
        Db.addStation("Ersa - Cap Corse", "7785",108,43.0056,9.3582,idFrance);
        Db.addStation("Bastia - Poretta", "7790",8,42.5509,9.4812,idFrance);
        Db.addStation("Sisco - Cap Sagro", "7791",111,42.798,9.4899,idFrance);


        // add default favorites stations
        Db.addFavorite(1, idAigle);
        Db.addFavorite(2, idBouveret);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // delete database
        db.execSQL(DbContract.Country.SQL_DELETE_TABLE);
        db.execSQL(DbContract.Station.SQL_DELETE_TABLE);
        db.execSQL(DbContract.Favorite.SQL_DELETE_TABLE);

        // recreate database
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}