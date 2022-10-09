# DoctorProSystemCare

# Εισαγωγή 

Για την δημιουργία της εφαρμογής χρησιμοποιήθηκε το IntelliJ IDEA 2021.1
(Ultimate Edition). Σε κάθε ενότητα δίνονται ακριβής οδηγίες για το πως
να ρυθμιστούν σωστά τα εργαλεία που χρησιμοποιούνται.

# Servers 

## Application Server

Για Application Server χρησιμοποιήθηκε ο Tomcat 9. Αρχικά εγκαταστάθηκε
και έπειτα με τη χρήση του IntelliJ έγινε παραμετροποίηση του. Το path
στο οποίο εγκαταστάθηκε ο Tomcat έπρεπε να προστεθεί στο παράθυρο
Settings -\> Build, Execution, Deployment -\> Application Servers
κάνοντας κλικ στο '+' πάνω αριστερά στο παράθυρο. Η σύνδεση με την
εφαρμογή έγινε μέσο του παραθύρου 'Run/Debug Configurations',
προσθέτοντας στη λίστα με τα configurations το Tomcat Local Server .

![image](https://user-images.githubusercontent.com/52785685/154546698-15c30b5e-0645-459c-a0ad-4329adcecce4.png)

Όμως, για να τρέξει τελικά η εφαρμογή μέσα από το IDEA πρέπει να
δημιουργηθεί ένα artifact. Στην προκείμενη περίπτωση δημιουργήθηκε το
'DoctorPro:war exploded' artifact μέσω του πεδίου Before Launch Tasks
στο ίδιο παράθυρο.

![image](https://user-images.githubusercontent.com/52785685/154546715-adee953e-c934-4ca1-9331-1890eca1959c.png)

## Database Server

Για Database Server έγινε χρήση του PostgresSQL. Η σύνδεση του με τον
Tomcat 9 έγινε με τη χρήση του JDBC driver, μετακινώντας το αρχείο
'postgresql-42.2.20.jar', που κατεβάσαμε από την ιστοσελίδα
'jdbc.postgresql.org', στον κατάλογο ./apache-tomcat-9.0.46/lib.

Εξαιτίας της ανάγκης να χρησιμοποιείται η ίδια βάση και λόγω ευκολίας
στη διαχείριση, προχωρήσαμε σε hosting τη βάσης σε πραγματικό server.
Για να την επίτευξη αυτής της ενέργειας χρησιμοποιήθηκε η υπηρεσία
Heroku. Η πρόσβαση και απόκτηση των βασικών πακέτων έγινε μέσο του
GitHub Student Developer Pack, όπου το Heroku αποτελεί ένα από τα
προνόμια του.

Η σύνδεση με το IntelliJ έγινε μέσο του παραθύρου
View/Tool-Windows/Databases, όπου προστέθηκε ως Data Source η PostgreSQL
και έπειτα χρησιμοποιώντας τα πιστοποιητικά που εκδόθηκαν από το Heroku,
ρυθμίστηκε η βάση. Μπορείτε να βρείτε τα στοιχεία αυτά στο αρχείο
'database_creds.txt'.

Παρακάτω παρουσιάζεται το παράθυρο όπου ρυθμίζεται η Βάση στο IntelliJ.

![image](https://user-images.githubusercontent.com/52785685/154546742-9cfbd0d4-7bf5-4ed7-b3ba-71ed94b113ca.png)

# Βάση Δεδομένων -- Μοντέλο Οντοτήτων-Σχέσεων 

![image](https://user-images.githubusercontent.com/52785685/154546760-a0ac7903-af0c-48af-af79-104133c1cf80.png)

Το Μοντέλο Οντοτήτων-Σχέσεων περιέχει τους βασικούς πίνακες της
εφαρμογής. Πιο συγκεκριμένα:

-   **Doctor:** Ο πίνακας των γιατρών περιέχει τον ΑΜΚΑ του γιατρού, ο
    οποίος αποτελεί πρωτεύον κλειδί του πίνακα και είναι ένα σύνολο από
    11 χαρακτήρες σταθερού μήκους. Τα υπόλοιπα πεδία, εκτός του
    admin_id, είναι πεδία χαρακτήρων μεταβλητού μήκους, το καθένα με
    διαφορετικό ανώτατο όριο. Τέλος, έχουμε το πεδίο admin_id, το οποίο
    σχετίζεται με τον πίνακα Admin και δηλώνει το ID του διαχειριστή που
    πρόσθεσε τον εκάστοτε γιατρό.

-   **Patient:** Ο πίνακας των ασθενών φέρει πολλές ομοιότητες με τον
    πίνακα των Γιατρών. Η μόνη διαφορά έγκειται στην μη ύπαρξη των
    πεδίων της ειδικότητας (specialty) και του admin_id, καθώς ο κάθε
    ασθενής είναι ο ίδιος υπεύθυνος για την εγγραφή του στο σύστημα.

-   **Available_Appointment:** Ο πίνακας αυτός αποθηκεύει τους
    διαθέσιμους χρόνους των γιατρών για ραντεβού. Κάθε ραντεβού έχει
    έναν μοναδικό 6ψήφιο ID για αναγνωριστικό, τους ΑΜΚΑ του γιατρού,
    την ημέρα (χωρίς ώρα) που θα διεξαχθεί το ραντεβού καθώς και την ώρα
    (χωρίς ζώνη ) που θα ξεκινήσει και θα τελειώσει. Σχετίζεται με τον
    πίνακα των Γιατρών καθώς από εκεί παίρνει τον ΑΜΚΑ του γιατρού με
    τον οποίο θα λαμβάνει χώρα το ραντεβού.

-   **Scheduled_Appointment:** Ο πίνακας αυτός αποθηκεύει τα ραντεβού
    που έχουν κλείσει οι ασθενείς με έναν γιατρό. Κάθε ραντεβού έχει
    έναν μοναδικό 6ψήφιο ID για αναγνωριστικό, τους ΑΜΚΑ του γιατρού και
    του ασθενή, την ημέρα (χωρίς ώρα) που θα διεξαχθεί το ραντεβού καθώς
    και την ώρα (χωρίς ζώνη ) που θα ξεκινήσει και θα τελειώσει.
    Σχετίζεται με τους πίνακες των Γιατρών και Ασθενών καθώς από εκεί
    παίρνει τον ΑΜΚΑ του γιατρού με τον οποίο λαμβάνει χώρα το ραντεβού
    και το ΑΜΚΑ του ασθένη, ώστε έτσι να υλοποιείται μια σύνδεση
    γιατρού-ασθενή για ένα ραντεβόυ.

-   **Admin:** Ο πίνακας των διαχειριστών είναι υπεύθυνος για την
    αποθήκευση των διαχειριστών. Για κάθε διαχειριστή αποθηκεύει έναν
    μοναδικό 6ψήφιο αριθμό που λειτουργεί ως πρωτεύον κλειδί, ένα
    username, έναν κωδικό, το email του και το χαρακτηριστικό SuperUser.
    Οι admin χωρίζονται σε δύο κατηγορίες: αυτούς που έχουν ανεβασμενό
    επίπεδο δικαιωμάτων και μπορούν να προσθέσουν καινούργιους admin και
    σε αυτούς που μπορούν να προσθέσουν μόνο καινούργιους γιατρούς. Αυτό
    το χαρακτηριστικό ελέγχεται με το πεδίο SuperUser που είναι τύπου
    Boolean. Tέλος, σχετίζεται με τον πίνακα των Γιατρών καθώς οι
    διαχειριστές είναι υπεύθυνοι για την προσθήκη, διαγραφή και
    επεξεργασία των γιατρών.

Η Βάση δημιουργήθηκε από την αρχή στον database server και
χρησιμοποιώντας το Mockaroo, μια δωρεάν εφαρμογή που τρέχει στον
φυλλομετρητή, δημιουργήθηκαν εικονικά δεδομένα για την βάση. Στον πίνακα
των ραντεβού χρησιμοποιήθηκαν στοιχεία που υπήρχαν ήδη από τους
υπόλοιπους πίνακες. Για λόγους ευκολίας, στο αρχείο 'test_users.txt' θα
βρείτε ασθενείς που έχουν περασμένα ραντεβού.

# Εκκίνηση και Παραμετροποίηση του Web Project 

Η δημιουργία του DoctorProSystemCare έγινε μέσα από το παράθυρο 'New
Project', επιλέγοντας το 'Java Enterprise' από τη λίστα. Ως template
επιλέχθηκε το 'Web Application' ώστε να γνωρίζει το IntelliJ το είδος
της εφαρμογής και να δημιουργήσει αυτόματα τους κατάλληλους καταλόγους
και αρχεία.

Από την Εργασία 1 έγινε εισαγωγή των Model τάξεων Patient, Doctor,
Appointment, Admin και User, οι οποίες τοποθετήθηκαν στο πακέτο
'com.WebFlexers.models'.

Στο πακέτο 'com.WebFlexers.servlets' δημιουργήθηκαν τα servlets που θα
χρησιμοποιηθούν για την εργασία. Ενδεικτικά τα PatientServlet,
DoctorServlet και AdminServlet.

# Υλοποίηση Μηχανισμού Login 

*Περιγραφή των κλάσεων που χρησιμοποιούνται για το login και λίγα λογία
για την διαδικασία*

# Κρυπτογράφηση κωδικού(password) 

Για την κρυπτογράφηση των κωδικών στην βάση δεδομένων έγινε χρήση του
αλγορίθμου **PBKDF2(**Password-Based Key Derivation Function 2**)**. Ο
παραπάνω αλγόριθμος κρίθηκε ικανοποιητικός λόγω της καλής αναλογίας
ασφάλειας και απόδοσης που παρέχει απέναντι σε επιθέσεις τύπου
brute-force και dictionary σε μία βάση δεδομένων. Όσο ανεβαίνει η ισχύς
των υπολογιστών, τόσο πιο ευάλωτο είναι ένα είδος κρυπτογράφησης. Το
πλεονέκτημα που παρέχει αυτός ο αλγόριθμος είναι η δυνατότητα να γίνει
πιο αργός με βάση των αριθμό των επαναλήψεων και ως αποτέλεσμα να
παράγεται ένα πιο δυνατό hash, καθιστώντας τον με αυτό τον τρόπο σχετικά
πιο ασφαλή στον παραπάνω κίνδυνο που αναφέρθηκε. Για την παραγωγή του
hash λαμβάνει ως παραμέτρους έναν αλγόριθμο SHA(Secure Hash Algorithms),
τον αριθμό των επαναλήψεων(iteration number) που θα πρέπει να γίνουν για
την παραγωγή του και το salt, το οποίο είναι ένας αριθμός από τυχαία
παραγμένα bytes.

![image](https://user-images.githubusercontent.com/52785685/154546780-3fdecbcd-eb4c-4a20-9ff4-a065fdb9ad73.png)

# Λειτουργίες Διαχειριστή 

Οι διαχειριστές χωρίζονται σε δύο κατηγορίες:

• Σε αυτούς που μπορούν να προσθέσουν καινούργιους και να διαγράψουν
υπάρχοντες γιατρούς.

• Και σε αυτούς που μαζί με τα παραπάνω, μπορούν να προσθέσουν και
άλλους admin. (SuperUsers)

Έχει υλοποιηθεί ένα κλιμακωτό σύστημα δικαιωμάτων(permissions) όπου
κάποιοι admin έχουν και την δεύτερη προαναφερθέντα λειτουργία.

# Λειτουργίες Γιατρού 

Οι γιατροί έχουν τις παρακάτω δυνατότητες:

• Προβολή των ραντεβού που εχουν κλείσει μαζί τους οι ασθενείς.

• Ακύρωση των ραντεβού με ασθενείς εφόσον είναι τουλάχιστον τρεις μέρες
μετά από την τρέχουσα ημερομηνία καθορισμένα.

• Προσθήκη διαθέσιμων ραντεβού για να μπορούν να επιλέξουν οι ασθενείς.

# Λειτουργίες Ασθενή 

Οι ασθενείς έχουν τις παρακάτω δυνατότητες:

• Προβολή των ραντεβού που έχουν κλείσει με τους γιατρούς.

• Ακύρωση προγραμματισμένων ραντεβού.

• Επιλογή κατηγορίας γιατρών και κλείσιμο ραντεβού μαζί τους.


![image](https://user-images.githubusercontent.com/52785685/154547244-75f42765-22c8-4f9f-8ec1-8da7c7f5186b.png)
# Βιβλιογραφικές Πηγές 

Template της σελίδας:
'https://w3layouts.com/template/alleviating-medical-category-bootstrap-responsive-web-template/'

Ρυθμιση του intellij ultimate για Web Applications με χρήση Glassfish(Η
ίδια ακριβως διαδικασία γίνεται και για τον Tomcat Application Server).
https://www.jetbrains.com/help/idea/creating-and-running-your-first-java-ee-application.html
