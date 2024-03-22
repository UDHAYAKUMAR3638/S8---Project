import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-view-production-lots',
  templateUrl: './view-production-lots.component.html',
  styleUrls: ['./view-production-lots.component.scss']
})
export class ViewProductionLotsComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public details: any, public dialogRef: MatDialogRef<ViewProductionLotsComponent>,
    private http: HttpClient
  ) { }

  materialName: string = '';
  lotNo: string = '';
  totalQuantity: number;
  producedDate: Date = new Date();

  save() {
    const obj = { materialName: this.materialName, lotNo: this.lotNo, totalQuantity: this.totalQuantity, producedDate: this.producedDate, inputLots: this.details };
    this.http.post("http://localhost:8080/production", obj).subscribe({
      next: (data) => {
        console.log(data);

      }
    })
  }
}
