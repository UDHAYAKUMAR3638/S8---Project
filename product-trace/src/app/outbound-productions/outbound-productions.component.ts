import { HttpClient, HttpParams } from '@angular/common/http';
import { Component } from '@angular/core';
import { ViewOutboundComponent } from '../view-outbound/view-outbound.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-outbound-productions',
  templateUrl: './outbound-productions.component.html',
  styleUrls: ['./outbound-productions.component.scss']
})
export class OutboundProductionsComponent {


  constructor(
    private http: HttpClient,
    public dialog: MatDialog,

  ) { }
  productions: any;

  ngOnInit() {
    const params = new HttpParams()
      .set('lotNo', '')
      .set('material', '')
    this.http.get("http://localhost:8080/production", { params }).subscribe({
      next: (data) => {
        this.productions = data;
      }
    })
  }

  openDialog(selectedProduction: any) {
    this.dialog.open(ViewOutboundComponent, {
      data: selectedProduction,
      width: "700px",
      height: "500px",
    })
  }
}
