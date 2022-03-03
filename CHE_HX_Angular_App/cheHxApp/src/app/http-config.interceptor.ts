import { Injectable, Injector } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpResponse,
  HttpErrorResponse,
  HttpEvent,
  HttpInterceptor} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { ToastMessgaeService } from './condensers/shared/toast-service/service/toast-messgae.service';
import { environment } from './../environments/environment';

@Injectable()
export class HttpConfigInterceptor implements HttpInterceptor {
  

  constructor(private injector: Injector, private toastMessgaeService: ToastMessgaeService) {}

  private handleError(err: HttpErrorResponse): Observable<any> {
    let errorMsg;
    if (err.error instanceof Error) {
        // A client-side or network error occurred. Handle it accordingly.
        errorMsg = `An error occurred: ${err.error.message}`;
    } else {
        // The backend returned an unsuccessful response code.
        // The response body may contain clues as to what went wrong,
        errorMsg = `Backend returned code ${err.status}, body was: ${err.error}`;
    }
    if (err.status === 401 || err.status === 403) {
      const par: any = parent;
      par.show_login_dialog();

    }
    console.error(errorMsg);
    return Observable.throw(errorMsg);
}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const started = new Date();
    let timeOut20 = setTimeout(() => {
      const toastSuccessMesage = {
        severity: 'error',
        summary: `It is taking more time than expected. Please wait`,
        key: 'generalError',
      };
        this.toastMessgaeService.showSuccess(toastSuccessMesage);
    }, environment.timeOut1*1000)

    let timeOut30 = setTimeout(() => {
      let toastSuccessMesage = {
        severity: 'error',
        summary: `Server is not responding. Please wait for some time or try again`,
        key: 'generalError',
      };
        this.toastMessgaeService.showSuccess(toastSuccessMesage);
    }, environment.timeOut2*1000)
    return next.handle(request).pipe(
      tap((evt) => {
          let res = '';
          if(evt instanceof HttpResponse) {
            clearTimeout(timeOut20)
            clearTimeout(timeOut30)
            if(!evt.body) {
              console.log('null response');
              throw new ErrorEvent('null body');
            }
          }

      }),
      catchError((error: HttpErrorResponse) => {
        clearTimeout(timeOut20);
        clearTimeout(timeOut30);
        if (error.error instanceof ErrorEvent) {
          // catch client-side error
          const toastSuccessMesage = {
            severity: 'error',
            summary: `Something went wrong. Please try again`,
            key: 'generalError',
          };
          this.toastMessgaeService.showSuccess(toastSuccessMesage);
        } else {
          // catch server-side error info
          const toastSuccessMesage = {
            severity: 'error',
            summary: `Server is not responding. Please try again`,
            key: 'generalError',
          };
          this.toastMessgaeService.showSuccess(toastSuccessMesage);
        }
        return throwError(error);
      })
    );
  }
}