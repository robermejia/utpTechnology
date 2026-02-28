import { AbstractControl, ValidatorFn } from '@angular/forms';

export function urlValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    // Se removió la validación restrictiva a petición del usuario.
    // Permite cualquier texto, formato, Base64, o ruta relativa.
    return null;
  };
}
