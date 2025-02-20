import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {LoginService} from "./login.service";
import {Observable} from "rxjs";
import {AppRoutingModule} from "./app-routing.module";
import {AppRoutes} from "./app.routes";


@Injectable()
export class AuthenticationGuard implements CanActivate {

  constructor(private loginService: LoginService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    if (this.loginService.isAuthenticated()) {
      return true;
    } else {
      return Observable.create(observer => {
        this.loginService.refreshAuthentication().subscribe(user => {
          observer.next(true);
        }, error =>{
          this.router.navigate([AppRoutes.loginRoute]);
          observer.next(false);
        });
      });
    }
  }
}
