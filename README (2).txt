Roles: Admin (DELbyID, MODbyId, READbyID, SEARCHbyID), User (READbyID(csak saját), POSTbyId)

APIApplication - main

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Enitity: id, setter(sender), getter(receiver), message, messageStatus(Enum), konstruktor létrehozva, argumentummentes megoldás szükségtelen

Hibakezelve a megegyező id-vel rendelkező üzenetek

LocalDatabase - LoggerFactory-val megoldva, repository.save() fgv. menti az xml-fájlba, az adatbázisban ideiglenesen tárolt adatokat

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Adatbázis tud: Adatokat menteni, POST/GET requestet fogadni és válaszolni, *byID fgv-k esetén lekezelve az egyező ID-k mentése

XML: Szintén mentésre, hosszútávú adattárolás és hard copy, menti az ID-t a Küldő és Fogadó személy nevét, az Üzenet szövegét és az Üzenet státuszát

MessageController: Listás megoldással megkülönbözteti a bemenő adatokat és a konstruktor meghívásával szabályosan menti őket, két egyező ID-jű üzenet mentését ellehetetleníti, throw-ol ilyen esetben

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

MODbyID fgv.: Megtartja az eredeti üzenet id-jét, kizárólag szöveg módosítására szolgál admin által, mind xml.ben mind db-ben

DELbyID fgv.: Admin fgv, törli a teljes Message-et, azaz id, setter, getter, üzenet és status törlésre kerül, frissíti az id-t a konzekvens üzenetekre

XMLReader: Message I/O Error lekezelve (chat, id, setter, getter, message, status)

READbyID fgv.: Admin fgv, id alapján Message-re keres, módosítás nem lehetséges vele, bármely üzenetre lehet keresni vele

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

MessageStatusEnum: SENT / SEEN / ANSWERED, SENT a küldéskor, GET request SEEN-re változtatja, de csak a címzett oldláról, Admin nem változtatja a státuszt csak MODbyID-val

ChatRepository egy JpaRepository<Entity, Long> paraméterekkel

DB: SpringBoot megoldás, username=sa, password=null



