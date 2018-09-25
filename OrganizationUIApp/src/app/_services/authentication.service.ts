import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthenticationService {
    private baseUrl = 'http://localhost:8079/auth-service';

    constructor(private http: HttpClient) { }

    login(username: string, password: string) {

        console.log('login istegi gonderilecek');

         return this.http.post<any>(this.baseUrl + '/login', { username: username, password: password })
           .map(res => {
                
            });
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('authorization');
    }

     private handleError(error: any): Promise<any> {
      console.error('Some error occured', error);
      return Promise.reject(error.message || error);
    }
}
