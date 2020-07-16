import * as dom  from './dom.js';
import * as api from './callServer.js';
import * as auth from './authorization.js';
import setUpLogin from './setUpLogin.js'

setUpLogin(dom, api, auth);