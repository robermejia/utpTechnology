import { AbstractControl, ValidatorFn } from '@angular/forms';

export function urlValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const urlRegex = /^(https?|ftp):\/\/[^\s/$.?#].[^\s]*$/i; // A common URL regex
    const valid = urlRegex.test(control.value);
    return valid ? null : { invalidUrl: { value: control.value } };
  };
}
