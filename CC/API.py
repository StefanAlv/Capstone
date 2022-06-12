import MySQLdb.cursors
from flask import Flask, render_template, request, redirect, url_for, session
from flask_mysqldb import MySQL
import MySQLdb.cursors
import re

app = Flask(__name__)

app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = '123'
app.config['MYSQL_DB'] = 'karsa'

mysql = MySQL(app)

@app.route('/')
@app.route('/login', methods=['GET', 'POST'])
def login():
    msg = ''
    if request.method == "POST":
        details = request.form
        email = details['email']
        password = details['password']
        cur = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
        cur.execute("SELECT * FROM accounts WHERE email = %s AND password = %s", (email, password))
        account = cur.fetchone()
        if account:
            session['loggedin'] = True
            session['id'] = account['id']
            session['email'] = account['email']
            return render_template('Logged in successfully')
        else:
            return render_template('Incorrect Email / Password!')

@app.route('/register', methods =['GET', 'POST'])
def register():
    msg = ''
    if request.method == 'POST':
        details = request.form
        email = details['email']
        password = details['password']
        cur = mysql.connection.cursor(MySQLdb.cursors.DictCursor)
        cur.execute("SELECT * FROM accounts WHERE email = %s",(email, ))
        account = cur.fetchone()
        if account:
            msg = 'Account already exists'
        elif not re.match(r'[^@]+@[^@]+\.[^@]+', email):
            msg = 'Invalid email address'
        elif not email or not password:
            msg = 'Form is still empty'
        else:
            cur.execute("INSERT INTO accounts VALUES (NULL, %s, %s", (email, password))
            mysql.connection.commit()
            msg = 'Register Successfully'
    elif request.method == 'POST':
        msg = 'Form is still empty'
    return render_template(msg = msg)
