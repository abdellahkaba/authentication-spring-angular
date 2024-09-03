import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services/authentication.service';
import { skipUntil } from 'rxjs';
import { CodeInputModule } from 'angular-code-input';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-activate-account',
  standalone: true,
  imports: [
    CodeInputModule,
    HttpClientModule,
    CommonModule
  ],
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.scss'] // Correction: styleUrl -> styleUrls
})
export class ActivateAccountComponent {
  message = '';
  isOkay = true;
  submitted = false;

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {}

  private confirmAccount(token: string) {
    this.authService.confirm({
      token
    }).subscribe({
      next: () => {
        this.message = 'Votre compte a été activé avec succès.\n' +
          'Vous pouvez maintenant procéder à la connexion';
        this.submitted = true;
      },
      error: () => {
        this.message = 'Le jeton a expiré ou n\'est pas valide';
        this.submitted = true;
        this.isOkay = false;
      }
    });
  }

  redirectToLogin() {
    this.router.navigate(['login']);
  }

  onCodeCompleted(token: string) {
    this.confirmAccount(token);
  }

  protected readonly skipUntil = skipUntil;
}
