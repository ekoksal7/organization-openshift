import { Injectable } from '@angular/core';
import { HttpRequest,HttpHeaders, HttpResponse, HttpErrorResponse, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import { Router, ActivatedRoute } from '@angular/router';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  
    constructor(
        private route: ActivatedRoute,
        private router: Router) { }
  
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      console.log('request');
      console.log(request);
      console.log("auth="+localStorage.getItem('authorization'));
      
        if (localStorage.getItem('authorization')!=null) {
          
             request = request.clone({
                headers: new HttpHeaders({
                  'Content-Type':  'application/json',
                  'Authorization': localStorage.getItem('authorization')
                })
              });
        }
      
      console.log(request);

       return next.handle(request).do((event: HttpEvent<any>) => {
      if (event instanceof HttpResponse) {
        // do stuff with response if you want
        console.log(event);
        console.log(event.headers.keys());
        console.log("auth header:"+event.headers.get('authorization'));
        
        if(event.url.includes("/login")){
          localStorage.setItem('authorization', event.headers.get('authorization'));
        }
        
        console.log(event);
      }
         
      return event;
    }, (err: any) => {
      if (err instanceof HttpErrorResponse) {
         console.log('error');
        console.log(err);
        if (err.status === 401) {
           if(!err.url.includes("/login")){
             
             console.log("login hatası degil")
             this.router.navigate(['/login']);
           }
          
        }
      }
    });

    }
}
