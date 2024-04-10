import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inbound',
  templateUrl: './inbound.component.html',
  styleUrls: ['./inbound.component.scss']
})
export class InboundComponent {

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private route: Router
  ) { }

  inboundForm: FormGroup;

  ngOnInit(): void {
    this.inboundForm = this.formBuilder.group({
      lotNo: ['', Validators.required],
      materialComposition: this.formBuilder.array([]),
      invoiceNo: [0, Validators.required],
      sellerName: ['', Validators.required],
      invoiceDate: [new Date()],
      addedBy: ['', Validators.required],
      totalQuantity: [0, Validators.required],
      remainingQuantity: [0],
    });

    this.addComposition();
  }
    
  Composition: FormGroup;

  addComposition(): void {
    this.Composition = this.formBuilder.group({
      materialType: ['', Validators.required],
      percentage: [0, Validators.required],
    });
    this.materialComposition.push(this.Composition);
  }

  removeComposition(index: number): void {
    this.materialComposition.removeAt(index);
  }

  get materialComposition(): FormArray {
    return this.inboundForm.get('materialComposition') as FormArray;
  }


  images: File[] = [];

  fileName: string[] = [];
  onFileUpload(event: Event) {
    const files = (event.target as HTMLInputElement).files;
    if (files && files.length > 0) {
      for (let i = 0; i < files.length; i++) {
        this.images.push(files[i]);
        this.fileName.push(files[i].name);
      }
    }
  }


  save(){

    const formData = new FormData();
    formData.append('lotNo', this.inboundForm.value.lotNo);
    formData.append('materialComposition', JSON.stringify(this.inboundForm.value.materialComposition));
    formData.append('invoiceNo', this.inboundForm.value.invoiceNo);
    formData.append('sellerName', this.inboundForm.value.sellerName);
    formData.append('invoiceDate', this.inboundForm.value.invoiceDate);
    formData.append('addedBy', this.inboundForm.value.addedBy);
    formData.append('totalQuantity', this.inboundForm.value.totalQuantity);
    formData.append('remainingQuantity', this.inboundForm.value.totalQuantity);

    for (let i = 0; i < this.images.length; i++) {
      formData.append('file', this.images[i]);
    }

    
    this.http.post("http://localhost:8080/inbound/save",formData).subscribe({
      next:(data)=>{
        console.log(data);
        Swal.fire("Inserted Inbound Successfully!");
        this.route.navigate(['production']);
      },
      error: () => {
        Swal.fire({
          icon: "error",
          title: "Oops...",
          text: "Something went wrong!",
        });
      }
    });
  }

}
