# FIT5042 Assignment
OzFlora - Native Plants Directory

## How to deploy
### Requirements
* Glassfish Server 4.1.1

### Starting the Enterprise Application
1. Create new derby database called *OzFlora* with username and password *fit5042*.
2. Clean and build all 4 projects.
3. Deploy the enterprise application (do not run, just deploy) to Glassfish server 4.1.1.
4. Once tables are created, run the *InsertDefaultData.sql* script to load default plants data.
5. Create a new admin user by running the *CreateAdmin.sql* script.
	This will create an admin user with email *admin@example.com* and password *Fit5042*.
6. Open Glassfish server admin console.
7. Under Configurations > server-config > Security > Realms, create a new jdbc realm with the following settings:

![JDBC Realm Settings](https://github.com/rqmok/FIT5042_Assignment/blob/master/jdbc-realm-settings.png "JDBC Realm Settings")

8. Once the realm is saved, it should be possible to start the enterprise application with user login and registration.
