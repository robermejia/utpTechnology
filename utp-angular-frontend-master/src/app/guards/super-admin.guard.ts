import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const superAdminGuard: CanActivateFn = (route, state) => {
    const authService = inject(AuthService);
    const router = inject(Router);

    const user = authService.getUser();
    if (authService.getToken() === null || user === null) {
        router.navigate(['/login']);
        authService.logout();
        return false;
    }

    // Super Admin is role 1
    if (user.id_rol === 1) {
        return true;
    }

    router.navigate(['/admin/productos']); // Redirect to regular dashboard
    return false;
};
